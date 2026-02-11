import multiprocessing as mp
import os
import numpy as np
import time

# mostre o número de processadores/núcleos
def show_cores():
    print("Números de processadores/cores: ", mp.cpu_count())


# crie uma fila com 10 números inteiros calcule e mostre o triplo de um número da fila
def get_triple(num: int):
    return num * 3

def triple_queue(fila):
    nome = mp.current_process().name
    print(f'{nome} recebeu: {fila.get()} pid: {os.getpid()}')   

def queue_triple():
    fila = mp.Queue()
    inteiros = range(1, 11)
    _ = [fila.put(i) for i in inteiros]
    processos = []	
    for proc in range(1, 11):
        processos.append(mp.Process(target=triple_queue, args=(fila,)))
    for proc in processos:
        proc.start()
    for proc in processos:
        proc.join()

# crie um pool de processos para executar a função de cálculo do triplo
# teste com o mesmo número de núcleos
def same_cores():
    inicio = time.time()
    pool = mp.Pool(mp.cpu_count())
    print(pool.map(get_triple, range(0, 100)))
    pool.close()
    pool.join()
    fim = time.time()
    print(f"PPID: {os.getppid()}")
    print(f"PID: {os.getpid()}")
    print(f"Tempo de execução: {fim - inicio:.2f} segundos")

# e com diferente
def diff_cores():
    extra = 16 
    inicio = time.time()
    pool = mp.Pool(mp.cpu_count() + extra)
    print(pool.map(get_triple, range(0, 100)))
    pool.close()
    pool.join()
    fim = time.time()
    print(f"PPID: {os.getppid()}")
    print(f"PID: {os.getpid()}")
    print(f"Tempo de execução: {fim - inicio:.2f} segundos")


if __name__ == "__main__":
    show_cores()
    queue_triple()
    same_cores()
    diff_cores()
