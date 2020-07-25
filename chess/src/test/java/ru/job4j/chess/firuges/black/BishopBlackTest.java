package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.Logic;
import ru.job4j.chess.OccupiedCellException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BishopBlackTest {

    @Test
    public void positionTest() {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        assertThat(bishop.position(), is(Cell.A1));
    }

    @Test
    public void copyTest() {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        Figure newBishop = bishop.copy(Cell.B4);
        System.out.println(newBishop.getClass());
        assertThat(newBishop.position(), is(Cell.B4));
    }

    @Test
    public void wayTest() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Cell[] actual = bishop.way(Cell.C1, Cell.G5);
        assertThat(actual, is(expected));
    }

    @Test (expected = IllegalStateException.class)
    public void whenWayNotDiagonal() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        bishop.way(Cell.C1, Cell.C5);
    }
}