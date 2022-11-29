coast = float(input("Введите стоимость слова: "))
words = input("Введите телеграмму: ").split()
result = len(words) * coast
print("Квитанция об оплате:", '\n',
      'Количество слов:', len(words), '\n',
      "Стоимость слова:", coast, '\n',
      "Оплата:", result) 
input()
