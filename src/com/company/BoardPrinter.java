package com.company;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardPrinter {

    private final List<String> board;
    private final int vertical;
    private String inline;
    private String pipes;
    private String line;

    public BoardPrinter(int vertical) {
        this.board = IntStream.range(0, 120).mapToObj(a -> new Integer(a + 1).toString()).collect(Collectors.toList());
        this.vertical = vertical;
        inline = "";
        line = "";
    }

    public String makePrettyBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(line);
        sb.append(pipes);
        sb.append("|");
        int n = 1;
        for (String cell : board) {
            sb.append(String.format("%3d |", n));
            if (n % vertical == 0) {
                sb.append("\n");
                sb.append(inline);
                if(n < board.size() - vertical + 1) {
                    sb.append(pipes);
                    sb.append("|");
                }
            }
            n += 1;
        }
        return sb.toString();
    }

    public void setup() {
        inline = makeInline();
        line = makeLine();
        pipes = makePipes();
    }

    private String makePipes() {
        StringBuilder pipes = new StringBuilder();
        List<String> downLineList2 = IntStream.range(0, vertical).mapToObj(a -> "|").collect(Collectors.toList());
        pipes.append(String.join("    ", downLineList2));
        pipes.append("    |\n");
        return pipes.toString();

    }

    private String makeLine() {
        StringBuilder line = new StringBuilder(" ");
        List<String> LineList = IntStream.range(0, vertical).mapToObj(a -> "____").collect(Collectors.toList());
        line.append(String.join(" ", LineList));
        line.append(" \n");
        return line.toString();

    }

    private String makeInline() {
        StringBuilder line = new StringBuilder();
        List<String> downLineList = IntStream.range(0, vertical).mapToObj(a -> "|____").collect(Collectors.toList());
        line.append(String.join("", downLineList));
        line.append("|\n");
        return line.toString();

    }
}

//DONE: Board need to have vertical size to be printed!!
//TODO: Huge need for having Cell class to simplify printing (no need for "n")
//TODO: board.size()!!