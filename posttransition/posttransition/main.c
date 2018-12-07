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

    for (int officeIndex = 0; officeIndex < town.offices_count; officeIndex++) {
        printf("\t%d:\n", officeIndex);

        for (int packageIndex = 0;
             packageIndex < town.offices[officeIndex].packages_count;
             packageIndex++) {
            printf("\t\t%s\n",
                   town.offices[officeIndex].packages[packageIndex].id);
        }
    }
}

void packageAppend(post_office * pOffice, const package * pPackage) {
    const int oldPackageCount = pOffice->packages_count;
    package * const oldPackages = pOffice->packages;
    const package * pOldPackage = &oldPackages[0];
    const package * const pOldPackageLimit = pOldPackage + oldPackageCount;

    const int newPackageCount = oldPackageCount + 1;
    package * const newPackages = malloc(sizeof(package) * newPackageCount);
    package * pNewPackage = &newPackages[0];

    while (pOldPackage < pOldPackageLimit) {
        *(pNewPackage++) = *(pOldPackage++);
    }

    *pNewPackage = *pPackage;

    pOffice->packages = newPackages;
    pOffice->packages_count = newPackageCount;

    free(oldPackages);
}

void packageDelete(post_office * pOffice, package * pPackage) {
    const int oldPackageCount = pOffice->packages_count;
    package * const oldPackages = pOffice->packages;

    const int newPackageCount = oldPackageCount - 1;
    const package * pNextRemainingPackage
        = pPackage + oldPackageCount - newPackageCount;
    package * const pPackageLimit = &oldPackages[newPackageCount];

    while (pPackage < pPackageLimit) {
        *(pPackage++) = *(pNextRemainingPackage++);
    }

    pOffice->packages_count = newPackageCount;
}

// Sometimes two post offices, even in different towns, may organize the
// following transaction: the first one sends all its packages to the second
// one. The second one accepts the packages that satisfy the weight condition
// for the second office and rejects all other ones. These rejected packages
// return to the first office back and are stored in the same order they were
// stored before they were sent. The accepted packages move to the tail of the
// second office's queue in the same order they were stored in the first office.

void send_all_acceptable_packages(town * pSrcTown, int srcOfficeIndex,
                                  town * pDstTown, int dstOfficeIndex) {
    post_office * const pSrcOffice = &pSrcTown->offices[srcOfficeIndex];
    post_office * const pDstOffice = &pDstTown->offices[dstOfficeIndex];
    int srcPackageIndex = 0;

    while (srcPackageIndex < pSrcOffice->packages_count) {
        package * const pSrcPackage = &pSrcOffice->packages[srcPackageIndex];

        if (pSrcPackage->weight >= pDstOffice->min_weight
            && pSrcPackage->weight <= pDstOffice->max_weight) {
            packageAppend(pDstOffice, pSrcPackage);
            packageDelete(pSrcOffice, pSrcPackage);
        } else {
            srcPackageIndex++;
        }
    }
}

town town_with_most_packages(town * towns, int townCount) {
    int maxCount = 0;
    int maxCountTownIndex = 0;

    for (int townIndex = 0; townIndex < townCount; townIndex++) {
        int count = 0;

        for (int officeIndex = 0; officeIndex < towns[townIndex].offices_count;
             officeIndex++) {
            count += towns[townIndex].offices[officeIndex].packages_count;
        }

        if (count > maxCount) {
            maxCount = count;
            maxCountTownIndex = townIndex;
        }
    }

    return towns[maxCountTownIndex];
}

town * find_town(town * towns, int townCount, char * name) {
    for (int townIndex = 0; townIndex < townCount; townIndex++) {
        if (strcmp(name, towns[townIndex].name) == 0) {
            return &towns[townIndex];
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
