package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!isDiagonal(source, dest)) {
            throw new IllegalStateException(
                    String.format("Could not move by diagonal from %s to %s", source, dest)
            );
        }
        int size = Math.abs(source.getX() - dest.getX());
        Cell[] steps = new Cell[size];
        int deltaX = (dest.getX() - source.getX()) / (Math.abs(dest.getX() - source.getX()));
        int deltaY = (dest.getY() - source.getY()) / (Math.abs(dest.getY() - source.getY()));
        for (int index = 0; index < size; index++) {
            steps[index] = Cell.findBy(source.getX() + deltaX * (index+1), source.getY() + deltaY*(index+1));
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        return Math.abs(source.getX() - dest.getX()) == Math.abs(source.getY() - dest.getY());
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
