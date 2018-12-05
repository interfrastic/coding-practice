//
//  main.c
//  posttransition
//
//  Created by Michael Krueger on 12/4/18.
//  Copyright © 2018 Michael Krueger. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#define MAX_STRING_LENGTH 6

struct package
{
    char* id;
    int weight;
};

typedef struct package package;

struct post_office
{
    int min_weight;
    int max_weight;
    package* packages;
    int packages_count;
};

typedef struct post_office post_office;

struct town
{
    char* name;
    post_office* offices;
    int offices_count;
};

typedef struct town town;


////////////////////////////////////////////////////////////////////////////////

#include <string.h>

void print_all_packages(town town) {
    printf("%s:\n", town.name);

    const int office_count = town.offices_count;

    for (int office_index = 0; office_index < office_count; office_index++) {
        printf("\t%d:\n", office_index);

        const post_office * const p_office = town.offices + office_index;
        const package * const packages = p_office->packages;
        const package * const p_package_limit
            = packages + p_office->packages_count;

        for (const package * p_package = packages; p_package < p_package_limit;
             p_package++) {
            printf("\t\t%s\n", p_package->id);
        }
    }
}

void append_package(post_office * p_office, const package * p_package) {
    const int old_package_count = p_office->packages_count;
    package * const old_packages = p_office->packages;
    const int new_package_count = old_package_count + 1;
    package * const new_packages = malloc(sizeof(package) * new_package_count);

    memcpy(new_packages, old_packages, sizeof(package) * old_package_count);
    p_office->packages = new_packages;
    free(old_packages);
    memcpy(new_packages + old_package_count, p_package,
           sizeof(package) * (new_package_count - old_package_count));
    p_office->packages_count = new_package_count;
}

void delete_package(post_office * p_office, package * p_package) {
    package * packages = p_office->packages;
    const int old_package_count = p_office->packages_count;
    const int new_package_count = old_package_count - 1;
    package * p_package_limit = packages + new_package_count;
    const package * p_next_remaining_package
        = p_package + old_package_count - new_package_count;

    while (p_package < p_package_limit) {
        *(p_package++) = *(p_next_remaining_package++);
    }
    p_office->packages_count = new_package_count;
}

// Sometimes two post offices, even in different towns, may organize the
// following transaction: the first one sends all its packages to the second
// one. The second one accepts the packages that satisfy the weight condition
// for the second office and rejects all other ones. These rejected packages
// return to the first office back and are stored in the same order they were
// stored before they were sent. The accepted packages move to the tail of the
// second office's queue in the same order they were stored in the first office.

void send_all_acceptable_packages(town * p_source_town, int source_office_index,
                                  town * p_target_town, int target_office_index) {
    post_office * p_source_office
        = p_source_town->offices + source_office_index;
    package * source_packages = p_source_office->packages;

    post_office * p_target_office
        = p_target_town->offices + target_office_index;
    int target_office_min_weight = p_target_office->min_weight;
    int target_office_max_weight = p_target_office->max_weight;
    int source_package_index = 0;

    while (source_package_index < p_source_office->packages_count) {
        package * const p_source_package
            = &source_packages[source_package_index];
        int source_package_weight = p_source_package->weight;

        if (source_package_weight >= target_office_min_weight
            && source_package_weight <= target_office_max_weight) {
            append_package(p_target_office, p_source_package);
            delete_package(p_source_office, p_source_package);
        } else {
            source_package_index++;
        }
    }
}

town town_with_most_packages(town * towns, int town_count) {
    int max_town_package_count = 0;
    town * p_town_with_max_town_package_count = towns;
    town * p_town_limit = towns + town_count;

    for (town * p_town = towns; p_town < p_town_limit; p_town++) {
        int town_package_count = 0;
        post_office * offices = p_town->offices;
        int office_count = p_town->offices_count;
        post_office * p_office_limit = offices + office_count;

        for (post_office * p_office = offices; p_office < p_office_limit;
             p_office++) {
            town_package_count += p_office->packages_count;
        }

        if (town_package_count > max_town_package_count) {
            max_town_package_count = town_package_count;
            p_town_with_max_town_package_count = p_town;
        }
    }

    return *p_town_with_max_town_package_count;
}

town * find_town(town * towns, int town_count, char * name) {
    town * p_town_limit = towns + town_count;

    for (town * p_town = towns; p_town < p_town_limit; p_town++) {
        if (strcmp(name, p_town->name) == 0) {
            return p_town;
        }
    }

    return NULL;
}

////////////////////////////////////////////////////////////////////////////////

int main()
{
    int towns_count;
    scanf("%d", &towns_count);
    town* towns = malloc(sizeof(town)*towns_count);
    for (int i = 0; i < towns_count; i++) {
        towns[i].name = malloc(sizeof(char) * MAX_STRING_LENGTH);
        scanf("%s", towns[i].name);
        scanf("%d", &towns[i].offices_count);
        towns[i].offices = malloc(sizeof(post_office)*towns[i].offices_count);
        for (int j = 0; j < towns[i].offices_count; j++) {
            scanf("%d%d%d", &towns[i].offices[j].packages_count, &towns[i].offices[j].min_weight, &towns[i].offices[j].max_weight);
            towns[i].offices[j].packages = malloc(sizeof(package)*towns[i].offices[j].packages_count);
            for (int k = 0; k < towns[i].offices[j].packages_count; k++) {
                towns[i].offices[j].packages[k].id = malloc(sizeof(char) * MAX_STRING_LENGTH);
                scanf("%s", towns[i].offices[j].packages[k].id);
                scanf("%d", &towns[i].offices[j].packages[k].weight);
            }
        }
    }
    int queries;
    scanf("%d", &queries);
    char town_name[MAX_STRING_LENGTH];
    while (queries--) {
        int type;
        scanf("%d", &type);
        switch (type) {
            case 1:
                scanf("%s", town_name);
                town* t = find_town(towns, towns_count, town_name);
                print_all_packages(*t);
                break;
            case 2:
                scanf("%s", town_name);
                town* source = find_town(towns, towns_count, town_name);
                int source_index;
                scanf("%d", &source_index);
                scanf("%s", town_name);
                town* target = find_town(towns, towns_count, town_name);
                int target_index;
                scanf("%d", &target_index);
                send_all_acceptable_packages(source, source_index, target, target_index);
                break;
            case 3:
                printf("Town with the most number of packages is %s\n", town_with_most_packages(towns, towns_count).name);
                break;
        }
    }
    return 0;
}
