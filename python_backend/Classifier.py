raw='1 lb ground chuck\n14 cup frozen chopped onions\n1 12 teaspoons garlic powder (to taste)\n1 teaspoon black pepper (to taste)\n1 teaspoon seasoning salt, divided into 1/4 teaspoons\n4 slices bacon, cooked crisp and broken in half\n4 slices American cheese\n4 slices tomatoes\nlettuce\n2 teaspoons mayonnaise, divided\n4 hamburger buns, split toasted if desired'
wordstoremove={'and', 'or', 'the', 'but', 'or','nor','for','yet', 'so', 'prepare',
               'preparation', 'high', 'protein', 'acid', 'product',
               'half', 'all', 'some', 'concentrate','replacer','seasoning','powder',
               'ground','black','slice','divided','into','taste','as'}
measurements=['teaspoon','teaspoons','tablespoon','tablespoons','slice','slices','cup','gallon','cups','gallons','pinch','spoonful']
def webToList(list):
    split=raw.split("\n")
    nlist=[]
    for item in split:
        nitem=item.lower().strip()
        for c in "()*&^%$#@!~`./<>?;':\"\'_-=+—1234567890":
            nitem=nitem.replace(c,"")
        nitem=nitem.split(',')[0]
        nitem=nitem.split()
        for m in measurements:
            if m in nitem:
                nitem.remove(m)
        nlist.append(" ".join(nitem).strip())
    return nlist
l=webToList(raw)
def parseList(input):
    output=[]
    file=open(input,'r')
    for line in file:
        pline = line.lower().strip()
        for c in "()*&^%$#@!~`,./<>?;':\"\'_-=+—":
            pline=pline.replace(c,"")
        sline=set(pline.split())
        output.extend(sline)
    outputs=set(output).difference(wordstoremove)
    return outputs
milkterms=parseList('milkwords.txt')
eggterms=parseList('eggwords.txt')
peanutterms=parseList('peanutwords.txt')
treenutterms=parseList('treenutwords.txt')
soyterms=parseList('soywords.txt')
wheatterms=parseList('wheatwords.txt')
fishterms=parseList('fishwords.txt')
meatterms=parseList('meatwords.txt')
def fishFree(list):
    outingr = set()
    for ingr in list:
        for term in fishterms:
            if term in ingr:
                outingr.add(ingr)
    return outingr
def milkFree(list):
    outingr= set()
    for ingr in list:
        for term in milkterms:
            if term in ingr:
                outingr.add(ingr)
    return outingr
def peanutFree(list):
    outingr= set()
    for ingr in list:
        for term in peanutterms:
            if term in ingr:
                outingr.add(ingr)
    return outingr
def treenutFree(list):
    outingr= set()
    for ingr in list:
        for term in treenutterms:
            if term in ingr:
                print(term)
                outingr.add(ingr)
    return outingr
def soyFree(list):
    outingr= set()
    for ingr in list:
        for term in soyterms:
            if term in ingr:
                outingr.add(ingr)
    return outingr
def eggFree(list):
    outingr= set()
    for ingr in list:
        for term in eggterms:
            if term in ingr:
                outingr.add(ingr)
    return outingr
def wheatFree(list):
    outingr= set()
    for ingr in list:
        for term in wheatterms:
            if term in ingr:
                outingr.add(ingr)
    return outingr
def notVegitarian(list):
    outingr= set()
    for ingr in list:
        for term in meatterms:
            if term in ingr:
                outingr.add(ingr)
    return outingr
def notVegan(list):
    a = set()
    a = a.union(milkFree(list))
    a = a.union(eggFree(list))
    a = a.union(fishFree(list))
    a= a.union(notVegitarian(list))
    return a
def allergenFree(list):
    a = set()
    a = a.union(peanutFree(list))
    a = a.union(treenutFree(list))
    a = a.union(milkFree(list))
    a = a.union(soyFree(list))
    a = a.union(wheatFree(list))
    a = a.union(eggFree(list))
    a = a.union(fishFree(list))
    return a

print(peanutFree(l))
print(treenutFree(l))
print(milkFree(l))
print(soyFree(l))
print(wheatFree(l))
print(eggFree(l))
print(fishFree(l))
print(notVegitarian(l))
print(notVegan(l))

print(allergenFree(l))