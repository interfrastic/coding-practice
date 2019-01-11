package net.avax.codingpractice.validnumber;

@SuppressWarnings({"WeakerAccess", "SwitchStatementWithTooFewBranches"})

////////////////////////////////////////////////////////////////////////////////
//
// LeetCode Valid Number problem:
//
// https://leetcode.com/problems/valid-number/
//
// First attempt: try very simple finite state machine (FSM).
//
// 1481 / 1481 test cases passed.
// Status: Accepted
// Runtime: 37 ms, faster than 84.42% of Java online submissions for Valid
// Number.
//
// https://leetcode.com/submissions/detail/200605168/

class Solution {
    private enum State {
        BEGIN,
        LEADING_SPACE,
        SIGN,
        INTEGER_PART,
        LONE_DECIMAL_POINT,
        DECIMAL_POINT,
        LONE_DECIMAL_PART,
        DECIMAL_PART,
        EXPONENT_MARKER,
        EXPONENT_SIGN,
        EXPONENT_PART,
        TRAILING_SPACE,
        END
    }

    public boolean isNumber(String s) {
        boolean result = true;
        State state = State.BEGIN;
        int index = 0;
        int indexLimit = s.length();

        while (state != State.END) {
            switch (state) {
                case BEGIN:
                    if (index >= indexLimit) {
                        result = false;
                        state = State.END;
                    } else {
                        char c = s.charAt(index++);

                        switch (c) {
                            case ' ':
                                state = State.LEADING_SPACE;
                                break;
                            case '+':
                            case '-':
                                state = State.SIGN;
                                break;
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                state = State.INTEGER_PART;
                                break;
                            case '.':
                                state = State.LONE_DECIMAL_POINT;
                                break;
                            default:
                                result = false;
                                state = State.END;
                                break;
                        }
                    }
                    break;
                case LEADING_SPACE:
                    if (index >= indexLimit) {
                        result = false;
                        state = State.END;
                    } else {
                        char c = s.charAt(index++);

                        switch (c) {
                            case ' ':
                                break;
                            case '+':
                            case '-':
                                state = State.SIGN;
                                break;
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                state = State.INTEGER_PART;
                                break;
                            case '.':
                                state = State.LONE_DECIMAL_POINT;
                                break;
                            default:
                                result = false;
                                state = State.END;
                                break;
                        }
                    }
                    break;
                case SIGN:
                    if (index >= indexLimit) {
                        result = false;
                        state = State.END;
                    } else {
                        char c = s.charAt(index++);

                        switch (c) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                state = State.INTEGER_PART;
                                break;
                            case '.':
                                state = State.LONE_DECIMAL_POINT;
                                break;
                            default:
                                result = false;
                                state = State.END;
                                break;
                        }
                    }
                    break;
                case INTEGER_PART:
                    if (index >= indexLimit) {
                        state = State.END;
                    } else {
                        char c = s.charAt(index++);

                        switch (c) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                break;
                            case '.':
                                state = State.DECIMAL_POINT;
                                break;
                            case 'e':
                                state = State.EXPONENT_MARKER;
                                break;
                            case ' ':
                                state = State.TRAILING_SPACE;
                                break;
                            default:
                                result = false;
                                state = State.END;
                                break;
                        }
                    }
                    break;
                case LONE_DECIMAL_POINT:
                    if (index >= indexLimit) {
                        result = false;
                        state = State.END;
                    } else {
                        char c = s.charAt(index++);

                        switch (c) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                state = State.LONE_DECIMAL_PART;
                                break;
                            default:
                                result = false;
                                state = State.END;
                                break;
                        }
                    }
                    break;
                case DECIMAL_POINT:
                    if (index >= indexLimit) {
                        state = State.END;
                    } else {
                        char c = s.charAt(index++);

                        switch (c) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                state = State.DECIMAL_PART;
                                break;
                            case 'e':
                                state = State.EXPONENT_MARKER;
                                break;
                            case ' ':
                                state = State.TRAILING_SPACE;
                                break;
                            default:
                                result = false;
                                state = State.END;
                                break;
                        }
                    }
                    break;
                case LONE_DECIMAL_PART:
                    if (index >= indexLimit) {
                        state = State.END;
                    } else {
                        char c = s.charAt(index++);

                        switch (c) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                break;
                            case 'e':
                                state = State.EXPONENT_MARKER;
                                break;
                            case ' ':
                                state = State.TRAILING_SPACE;
                                break;
                            default:
                                result = false;
                                state = State.END;
                                break;
                        }
                    }
                    break;
                case DECIMAL_PART:
                    if (index >= indexLimit) {
                        state = State.END;
                    } else {
                        char c = s.charAt(index++);

                        switch (c) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                break;
                            case 'e':
                                state = State.EXPONENT_MARKER;
                                break;
                            case ' ':
                                state = State.TRAILING_SPACE;
                                break;
                            default:
                                result = false;
                                state = State.END;
                                break;
                        }
                    }
                    break;
                case EXPONENT_MARKER:
                    if (index >= indexLimit) {
                        result = false;
                        state = State.END;
                    } else {
                        char c = s.charAt(index++);

                        switch (c) {
                            case '+':
                            case '-':
                                state = State.EXPONENT_SIGN;
                                break;
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                state = State.EXPONENT_PART;
                                break;
                            default:
                                result = false;
                                state = State.END;
                                break;
                        }
                    }
                    break;
                case EXPONENT_SIGN:
                    if (index >= indexLimit) {
                        result = false;
                        state = State.END;
                    } else {
                        char c = s.charAt(index++);

                        switch (c) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                state = State.EXPONENT_PART;
                                break;
                            default:
                                result = false;
                                state = State.END;
                                break;
                        }
                    }
                    break;
                case EXPONENT_PART:
                    if (index >= indexLimit) {
                        state = State.END;
                    } else {
                        char c = s.charAt(index++);

                        switch (c) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                break;
                            case ' ':
                                state = State.TRAILING_SPACE;
                                break;
                            default:
                                result = false;
                                state = State.END;
                                break;
                        }
                    }
                    break;
                case TRAILING_SPACE:
                    if (index >= indexLimit) {
                        result = true;
                        state = State.END;
                    } else {
                        char c = s.charAt(index++);

                        switch (c) {
                            case ' ':
                                break;
                            default:
                                result = false;
                                state = State.END;
                                break;
                        }
                    }
                    break;
                default:
                    throw new AssertionError("Undefined state " + state);
            }
        }

        return result;
    }
}
