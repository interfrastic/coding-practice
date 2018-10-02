from operator import itemgetter
from pprint import pprint

import distance
import matplotlib.pyplot as plt
import numpy as np
from sklearn.cluster import AffinityPropagation


# Group similar strings using the method outlined here:
#
# https://stats.stackexchange.com/a/158090

def group_strings(strings):
    strings = np.asarray(list(strings))
    lev_similarity = -1 * np.array(
        [[distance.levenshtein(w1, w2) for w1 in strings] for w2 in strings])

    show_similarity_matrix(strings, lev_similarity, "Levenshtein similarity")

    clustering = AffinityPropagation(affinity='precomputed')
    clustering.fit(lev_similarity)

    groups = []

    for cluster_id in np.unique(clustering.labels_):
        indices = np.nonzero(clustering.labels_ == cluster_id)
        cluster = np.unique(strings[indices])
        groups.append(sorted(cluster))

    groups.sort(key=itemgetter(0))

    return groups


# This method is called when the module is run as a standalone script.

def main():
    strings = [
        'foo',
        'bar',
        'baz',
        'qux',
        'quux',
        'quuz',
        'corge',
        'grault',
        'garply',
        'waldo',
        'fred',
        'plugh',
        'xyzzy',
        'thud'
    ]

    for grouped_strings in group_strings(strings):
        pprint(grouped_strings)


# Show a similarity matrix as a heat map, as outlined here:
#
# https://matplotlib.org/gallery/images_contours_and_fields/image_annotated_heatmap.html#a-simple-categorical-heatmap

def show_similarity_matrix(strings, similarity, description=None):
    fig, ax = plt.subplots()
    ax.imshow(similarity)

    # We want to show all ticks . . .

    ax.set_xticks(np.arange(len(strings)))
    ax.set_yticks(np.arange(len(strings)))

    # . . . and label them with the respective list entries.

    ax.set_xticklabels(strings)
    ax.set_yticklabels(strings)

    # Rotate the tick labels and set their alignment.

    plt.setp(ax.get_xticklabels(), rotation=45, ha='right',
             rotation_mode='anchor')

    # Loop over data dimensions and create text annotations.

    for i in range(len(strings)):
        for j in range(len(strings)):
            ax.text(j, i, similarity[i, j], ha='center', va='center',
                    color='w')

    if description is not None:
        ax.set_title(description)

    fig.tight_layout()
    plt.show()


# Execute the main() method if this module is being run as a standalone script.

if __name__ == "__main__":
    main()
