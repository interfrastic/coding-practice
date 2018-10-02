from operator import itemgetter
from pprint import pprint

import distance
import numpy as np
from sklearn.cluster import AffinityPropagation


# Group similar strings using the method outlined here:
#
# https://stats.stackexchange.com/a/158090

def group_strings(strings):
    strings = np.asarray(list(strings))
    lev_similarity = -1 * np.array(
        [[distance.levenshtein(w1, w2) for w1 in strings] for w2 in strings])

    pprint(lev_similarity)

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


if __name__ == "__main__":
    main()
