# Gates

This repo is used for out final project in CS362.

Cool site to check out for git repo help:

http://dont-be-afraid-to-commit.readthedocs.io/en/latest/git/commandlinegit.html



Idea:

A game based off of boolean logic. Turn based.

Includes logic gates AND, OR, NOR, XNOR, NAND and XOR. 12 pieces per player.

Object is to remove all pieces from opponent.



Rules:

AND and NOR gates can move up and down 3 spaces and diagonal 2.

XOR and XNOR gates can move up and down 2 spaces and diagonal 1.

OR and NAND gates can move up and down 1 space and cannot move diagonal.

Black squares represent a 1 and white squares represent a 0. Use these as inputs to boolean gate.

If output is a 1 and your opponent's output is a 0, you win and your opponent's piece is removed.

If output of both is either a 1 or a 0, both pieces are removed.
