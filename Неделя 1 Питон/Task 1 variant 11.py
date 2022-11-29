import random
def Transporent(matrix):
    new_matrix_T = []
    for i in range(len(matrix[1])):
        new_matrix_T.append([])
        for j in range(len(matrix)):
            new_matrix_T[i].append(matrix[j][i])
    return new_matrix_T
def DeletZeros(matrix):
    new_matrix = []
    for i in range(len(matrix)):
        zero_row = 0
        for j in range(len(matrix[i])):
            if(matrix[i][j] == 0):
                zero_row = zero_row + 1
        if (zero_row != len(matrix[i])):
            new_matrix.append(matrix[i])
    return new_matrix

try:
    n = int(input("Введите размер матрицы: "))
    matrix = []
    for i in range(n):
        matrix.append([])         
        for j in range(n):
            matrix[i].append(random.randint(-n, n))
    matrix_T = Transporent(matrix)
    matrix_clear_colums = DeletZeros(matrix_T)
    matrix_T = Transporent(matrix_clear_colums)
    matrix_clear_rows = DeletZeros(matrix_T)
    print("Исходная матрица:")
    for i in range(n):       
        for j in range(n):
            print(matrix[i][j], end='  ')
        print("\n")
    print("Уплотнённая матрица:")
    for i in range(len(matrix_clear_rows)):       
        for j in range(len(matrix_clear_rows[i])):
            print(matrix_clear_rows[i][j], end='  ')
        print("\n")
except Exception as e:
    print("Некорректная матрица")
input()
