class House:
    def __init__(self, doors = [], windows = []):  
        self._doors = doors
        self._windows = windows
    """Переопределение equals"""
    def __eq__(self, other):
        return (self._doors, self._windows) == (other._doors, other._windows)
    """Переопределение toString"""
    def __str__(self):
         return "Класс House"
    def addDoor(self, door):
        self._doors.append(door)
    def addWindow(self,window):
        self._windows.append(window)
    def getDoorsCount(self):
        return len(self._doors)
    def getWindowsCount(self):
        return len(self._windows)
    def closeDoor(self, door):
        print("Дверь закрыта")
        door.setIsOpen(False)
class Door:
    def __init__(self, width = 0, height = 0, material = '', isOpen = True):  
        self._width = width
        self._height = height
        self._material = material
        self._isOpen = isOpen
    """Переопределение equals"""
    def __eq__(self, other):
        return (self._width, self._height,self._material, self._isOpen) == (other._width, other._height,other._material, other._isOpen)
    """Переопределение hashCode"""
    def __hash__(self):
        return hash((self._width, self._height,self._material, self._isOpen))
    """Переопределение toString"""
    def __str__(self):
         return "Класс Door"
    def getWidth(self):
        return self._width
    def getHeight(self):
        return self._height
    def getMaterial(self):
        return self._material
    def getIsOpen(self):
        return self._isOpen
    def setWidth(self, width):
        self._width = width  
    def setHeight(self, height):
        self._height = height  
    def setMaterial(self, material):
        self._material = material
    def setIsOpen(self, isOpen):
        self._isOpen = isOpen  
class Window:
    def __init__(self, width = 0, height = 0, glazing = ''):  
        self._width = width
        self._height = height
        self._glazing = glazing
    """Переопределение equals"""
    def __eq__(self, other):
        return (self._width, self._height, self._glazing) == (other._width, other._height, other._glazing)
    """Переопределение hashCode"""
    def __hash__(self):
        return hash((self._width, self._height, self._glazing))
    """Переопределение toString"""
    def __str__(self):
         return "Класс Window"
    def getWidth(self):
        return self._width
    def getHeight(self):
        return self._height
    def getMaterial(self):
        return self._material
    def setWidth(self, width):
        self._width = width  
    def setHeight(self, height):
        self._height = height  
    def setGlazing(self, glazing):
        self._glazing = glazing

Door1 = Door(75, 200, "Дуб")
Door2 = Door(85, 210, "Фанера")
Door3 = Door(85, 210, "Фанера")

print("Работает ли переопределение equals в классе Door:", Door2 == Door3)
print("Работа hashCode в классе Door:", hash(Door2))
print("Работа toString в классе Door:", end = " ")
print(Door2)
print("_____________________________________________________")

Window1 = Window(175, 145, "Двойной степлопакет")
Window2 = Window(95, 145, "Одинарный степлопакет")
Window3 = Window(95, 145, "Одинарный степлопакет")

print("Работает ли переопределение equals в классе Window:", Window2 == Window3)
print("Работа hashCode в классе Window:", hash(Window2))
print("Работа toString в классе Window:", end = " ")
print(Window2)
print("_____________________________________________________")

House1 = House([Door1, Door2],[Window1, Window2])
House2 = House([Door1, Door2],[Window1, Window2])

print("Работает ли переопределение equals в классе House:", House1 == House2)
print("Работа toString в классе House:", end = " ")
print(House1)
print("_____________________________________________________")

print("Открытость первой двери: ",Door1.getIsOpen())
print("Количество дверей: ", House1.getDoorsCount())
print("Количество окон: ",House1.getWindowsCount())
House1.closeDoor(Door1)
print("Открытость первой двери: ",Door1.getIsOpen())
input()
