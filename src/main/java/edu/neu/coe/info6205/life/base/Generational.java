package edu.neu.coe.info6205.life.base;

import java.util.function.BiConsumer;

interface Generational<T> {

		void generation(long generation, BiConsumer<Long, T> monitor);
}
