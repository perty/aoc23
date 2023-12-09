# Advent of code 2023

## Introduction

Using AI, I will solve the Advent of Code 2023. Perhaps not.

Language: Java.

IDE: Visual Studio Code, IntelliJ and Cursor.

## Day 1

I had some problems with understanding the specification, it was a bit vague on what to do when the digits written as words overlapped, eg "eightwo". Not until I read on Reddit that both eight and two should be counted did I understand that I had made the opposite assumption.

Naturally, when I misunderstood the specification, the AI did the same.

I had a try with Cursor, but it was a disappointment. GitHub Copilot results in less amount of clicks to change the code. I would have expected the opposite.

## Day 2

The first part was solved by Github Copilot without changes. I just copied the description and it worked.

The second part was solved by Github Copilo as well, straight off. I am impressed.

## Day 3

The first part was solved by Github Copilot after a few iterations of the example where I just told it that it was wrong. When it succeeded, the real input was solved as well.

The second part, it could not solve without my help. Initially, I just fed back that it was wrong, but it failed to 
make any progress. I broke down the problem into smaller parts, and then it could solve it. I was worried that using
a set to avoid duplicates could be a problem, but it was not.

## Day 4

The first part was solved by Github Copilot after a few iterations where it had problems with parsing the input. I just gave it some observations.

Part 2 was solved at first try. The only problem was that the response length exceeded a limit. However, it was easy to figure out the last few lines of the code.

The whole conversation is in the file [day4.md](prompts/day4.md).

## Day 5

In the first part, it started with a suggestion in Python. Funny. It needed some help to get the parsing of the input right. On the real input, the code had to use long instead of int, but that was all.

I was impressed that it understood the specification so well. A lot faster than I did.

The second part offered a challenge in terms of memory usage and performance. It solved it correctly for the test input at first try. The real input caused an out-of-memory error. It then suggested a solution that hadn't terminated in 5 minutes. The last suggestion clocked in under 4 minutes. It explained the complexity: 

_"The current solution has a time complexity of O(n*m), where n is the number of seeds and m is the number of mappings... One way to improve the performance is to sort the mappings by their source start values. This way, when mapping a value, you can use binary search to quickly find the mapping that applies to the value, instead of having to check each mapping in turn. This can reduce the time complexity of the mapping operation from O(m) to O(log m)..."_

The whole conversation is in the file [day5.md](prompts/day5.md).

## Day 6

First part, AI has serious problems with parsing the input. I let it try several times until I helped it with a comment about repetead whitespace is a single separator. Then it solved it. It was obvious that it was taking chances, focusing on the algorithm and not the input.
