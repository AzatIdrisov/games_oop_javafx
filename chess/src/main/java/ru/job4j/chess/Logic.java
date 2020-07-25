package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        figures[index++] = figure;
    }

    public void move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        int index = findBy(source);
        Cell[] steps = figures[index].way(source, dest);
        if (!isFree(steps)) {
            figures[index] = figures[index].copy(source);
            throw new OccupiedCellException("Way is not free");
        }
        figures[index] = figures[index].copy(dest);
    }

    private boolean isFree(Cell[] steps) {
        boolean free = true;
        for (int i = 0; i < steps.length; i++) {
            for (int j = 0; j < figures.length; j++) {
                if (figures[j] != null) {
                    if (figures[j].position().equals(steps[i])) {
                        free = false;
                    }
                }
            }
        }
        return free;
    }

    public void clean() {
        Arrays.fill(figures, null);
        index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        for (int index = 0; index != figures.length; index++) {
            if (figures[index] != null && figures[index].position().equals(cell)) {
                return index;
            }
        }
        throw new FigureNotFoundException();
    }
}
