package br.ucsal.util.interfaces;

import br.ucsal.util.exception.InvalidElementException;

public interface List<T> {

	/**
	 * Adiciona um elemento no final da lista atual.
	 * 
	 * @param element elemento a ser adicionado.
	 * @throws InvalidElementException exceção que deve ser lançada caso o elemento
	 *                                 adicionado seja inválido (nulo, por exemplo).
	 */
	void add(T element) throws InvalidElementException;

	/**
	 * Retorna o elemento da lista, na posição especificada.
	 * 
	 * @param index posição do elemento a ser retornado
	 * @return o elemento da posição especificada
	 */
	T get(int index);

	/**
	 * Retorna a quantidade de elementos da lista. Esse método facilitou a
	 * implementação do addAll, mas não era essencial. Contruimos uma implementação
	 * de addAll com size e outra sem o size.
	 * 
	 * @return quantidade de elementos na lista.
	 */
	int size();

	/**
	 * Limpa a lista.
	 */
	void clear();

}