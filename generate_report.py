import csv

file = open('evosuite-report/statistics.csv')

csv_reader = csv.reader(file)

header = next(csv_reader)

rows = []
for row in csv_reader:
    rows.append(row)

readme = open('README.md', 'w')
readme.write('|')
for h in header:
    readme.write(h + '|')
readme.write('\n')
readme.write('|')
for h in header:
    readme.write(':----:|')
readme.write('\n')
for r in rows:
    readme.write('|')
    for i in r:
        readme.write(i + '|')
    readme.write('\n')
