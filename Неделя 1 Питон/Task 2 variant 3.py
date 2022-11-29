import math
class Equation(object):
    def __init__(self, a, b, c):
        self.a = a
        self.b = b
        self.c = c
    
    def root(self):
        discr = self.b ** 2 - 4 * self.a * self.c
        if discr > 0:
            x1 = (-self.b + math.sqrt(discr)) / (2 * self.a)
            x2 = (-self.b - math.sqrt(discr)) / (2 * self.a)
            return [round(x1, 2), round(x2, 2)]
        elif discr == 0:
            x = -self.b / (2 * self.a)
            return [round(x, 2)]
        else:
            return None
    def extreme(self):
        x = -(self.b / (2 * self.a))
        y = self.a * x ** 2 + self.b * x + self.c
        if self.a >= 0:
            return print("Точка минимума[",x,', ',y,"]", sep='')
        else:
            return print("Точка максимума[",x,', ',y,"]", sep='') 
    def interval(self):
        x = -(self.b / (2 * self.a))
        if self.a >= 0:
            return print("Убывает на промежутках[-∞, ",x,']', '\n', "Возрастает на промежутках[",x,', ∞]', sep='')
        else:
            return print("Возрастает на промежутках[-∞, ",x,']', '\n', "Убывает на промежутках[",x,', ∞]', sep='')
    
if __name__ == "__main__":

    equation1 = Equation(-2,8,3)
    print("Корни:",equation1.root()) 
    
    equation2 = Equation(1,17,18)
    equation2.extreme()
    equation3 = Equation(1,2,1)
    equation3.interval()
    equations = [equation1, equation2, equation3]
    roots = []
    for i in equations:
        if (i.root() != None):
            for j in i.root():
                roots.append(j)

print("Меньший корень:", min(roots), "Больший корень:", max(roots)) 
input()
