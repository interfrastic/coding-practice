# input: ()
# output: true

# input: (())
# output: true

# input: (()())
# output: true

# input: (
# output: false

# input: ())
# output: false


def is_balanced(string):
    end_for_begin = {
        '(': ')',
        '[': ']',
        '{': '}',
    }
    stack = []

    for char in string:
        for (begin, end) in end_for_begin.items():
            if char == begin:
                stack.append(char)
            elif char == end:
                if len(stack) == 0 or stack.pop() != begin:
                    return False

    return len(stack) == 0


inputs = [
    '()',
    '(())',
    '(()())',
    '(',
    '())',
    '()',
    '({})',
    '({}[])',
    '(]',
    '({)}'
]

for input in inputs:
    result = is_balanced(input)

    print("{}\t{}".format(input, result))
