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
    def getDors(self):
        return self._doors
    def getWindows(self):
        return self._windows
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
         return ("Класс Door" )
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
Door3 = Door(65, 210, "Осина")
Window1 = Window(175, 145, "Двойной степлопакет")
Window2 = Window(95, 145, "Одинарный степлопакет")
Window3 = Window(115, 165, "Двойной степлопакет")

House1 = House([Door1, Door2],[Window1, Window2])
House2 = House([Door1, Door2, Door3],[Window1, Window2, Window3])

Houses = [House1, House2]
    
while True:
    try:
        house_number = int(input("Введите номер: "))
        if (house_number > len(Houses) or house_number <= 0):
            print("Да нет такого дома!")
            continue
        print("Дверей в этом доме: ", Houses[house_number - 1].getDoorsCount())
        for i in range(Houses[house_number - 1].getDoorsCount()):
            print("_______________________________")
            print("Номер двери: ", i+1)
            print("Ширина: ", Houses[house_number - 1].getDors()[i].getWidth())
            print("Высота: ", Houses[house_number - 1].getDors()[i].getHeight())
            print("Материал: ", Houses[house_number - 1].getDors()[i].getMaterial())
            print("Открытость: ", Houses[house_number - 1].getDors()[i].getIsOpen())
        print("_______________________________")
        print("Окон в этом доме: ", Houses[house_number - 1].getWindowsCount())
    except Exception as e:
        print("Неверно введён номер дома!")

 


