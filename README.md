# Heroes-League

Aceasta aplicatie simuleaza un joc MMO-style.

Exista mai multe tipuri de eroi, fiecare avand un anumit set de abilitati si care se misca pe o harta 2D.
La inceputul jocului, eroii nostri sunt plasati pe harta in locuri bine definite.
Desfasurarea jocului presupune existenta unor runde cu durata unitate in care toti eroii vor executa cate o miscare bine definita pe harta.
Cand doi eroi ajung in acelasi loc, ei se vor lupta.
in cadrul unei runde, fiecare erou combatant isi va folosi toate abilitatile disponibile impotriva adversarului, o singura data.
Dupa lupta, ei isi vor vedea de drum incepand cu runda urmatoare.
La sfarsitul jocului (un numar stabilit de runde, cu locatii initiale si miscari stabilite), aplicatia se va uita la eroii ramasi in viata.

	Harta

Harta pe care se desfasoara jocul este un careu 2D (dreptunghi), compus din locatii (“patratele”) de dimensiune unitate. 
Fiecare locatie are un anumit tip, ceea ce ii confera un set de proprietati; tipurile de locatii disponibile sunt:
- Land
- Volcanic
- Desert
- Woods

	Eroii
	
Exista mai multe tipuri de eroi:

- Knight
- Pyromancer
- Rogue
- Wizard

Toti eroii au doua proprietati de baza si anume un numar de hit points(HP) si un numar de puncte de experienta (XP).
Pe langa acestea, exista si un mecanism de level-up in functie de experienta castigata in urma luptelor.

	Experienta si level up

Toate personajele au XP iniţial = 0, corespunzator nivelului 0. 
in momentul in care un personaj caştiga o lupta (isi omoara adversarul), XP-ul sau va creşte dupa urmatoarea formula:
XP_Winner = XP_Winner + max(0, 200 - (Level_winner - Level_loser) * 40).

Se poate intampla ca eroii sa se omoare reciproc. in acest caz, ambii vor primi XP-ul corespunzator.
Cresterea in nivel are loc in momentul in care se depaseste un prag de XP, ce se va calcula dupa urmatoarea formula:
XP_level_up = 250 + Level_curent * 50.
La avansarea in nivel a unui erou, acesta va reveni la 100% HP.

	Abilitati
	
Fiecare tip de erou are un anumit set de abilitati, ai caror parametri sau ale caror efecte depind de:
- terenul pe care se desfasoara lupta (land modifiers);
- personajul asupra caruia actioneaza (race modifier) 
Amplificatorii sunt multiplicativi, indiferent daca abilitatea da flat damage sau procent din stats-urile adversarului!
Mai exact, mai multi aplificatori sunt legati in calcule prin operatia de inmultire, nu prin operatia de adunare.
La nivelul 0 toate abilitaţile au doar efectul de baza!

	Pyromancer

HP: 500 initial, +50 per nivel.

Abilitatea 1: Fireblast
	
Damage: 350, +50/level

Victima		Modificator
Rogue		-20%
Knight		+20%
Pyromancer	-10%
Wizard		+5%

Abilitatea 2: Ignite
	
Runda curenta: 			damage: 150, +20/level
Urmatoarele 2 runde:	damage: 50 / runda, +30/level.

Modificatorii de mai jos se aplica atat pentru base damage, cat si pentru damage periodic:

Victima		Modificator
Rogue		-20%
Knight		+20%
Pyromancer	-10%
Wizard		+5%
Pe terenul de tip Volcanic abilitaţile Pyromancer-ului dau cu 25% mai mult damage.

	Knight
	
HP: 900 initial, +80/level.

Abilitatea 1: Execute

Daca adversarul are un numar de HP mai mic decat o anumita limita, va fi ucis instantaneu (damage-ul dat este egal cu HP-ul adversarului).
- base damage:200, +30/level
- HP limit: 20% * viata teoretic maxima a victimei la nivelul ei; +1% /level, pana la un maxim de 40%
Damage-ul (nu si limita de viata) se modifica in functie de victima ca mai jos:

Victima		Modificator
Rogue		+15%
Knight		+0%
Pyromancer	+10%
Wizard		-20%

Abilitatea 2: Slam
 
- base damage: 100 base damage. +40 /level
- incapacitarea adversarului (imposibilitate de miscare) pentru urmatoarea runda.

Victima		Modificator
Rogue		-20%
Knight		+20%
Pyromancer	-10%
Wizard		+5%

Pe terenul de tip land Knight primeste 15% bonus damage.

	Wizard
	
HP: 400 initial, +30 per nivel.

Abilitatea 1: Drain
- scade din viata adversarului proportional cu cat are deja.
- procent: 20%, +5% /level
- HP de baza: min(0.3 * OPPONENT_MAX_HP, OPPONENT_CURRENT_HP)
- damage = procent * min(0.3 * OPPONENT_MAX_HP, OPPONENT_CURRENT_HP)

Modificatorii de mai jos se aplica asupra variabilei procent si ei sunt multiplicativi

Victima		Modificator
Rogue		-20%
Knight		+20%
Pyromancer	-10%
Wizard		+5%

Abilitatea 2: Deflect
- da damage egal cu un procent din damage-ul total (fara race modifiers) pe care il primeste de la adversar
- procent: 35%, +2% /level, pana la un maxim de 70%
- nu are niciun efect asupra unui Wizard (doi eroi de tip Wizard nu isi dau reciprc/recursiv damage)

Victima		Modificator
Rogue		+20%
Knight		+40%
Pyromancer	+30%
Wizard		N/A

Pe terenul de tip Desert abilitatile Wizard-ului sunt cu 10% mai puternice.

	Rogue

HP : 600 initial, +40 /level

Abilitatea 1: Backstab
- damage in runda curenta, cu posibilitate de critical hit.
- base damage: 200, +20 dmg /level.
- O data la 3 lovituri (lovitura = aplicat Backstab, pe orice teren) Rogue-ul poate da 1.5x damage,
doar daca in acel moment se afla pe terenul de tip Woods, altfel se reia ciclul.

Victima		Modificator
Rogue		+20%
Knight		-10%
Pyromancer	+25%
Wizard		+25%

Abilitatea 2:Paralysis
- damage prelungit + incapacitarea adversarului pentru un numar de runde
- damage/runda: 40, +10/level - se aplica in runda curenta (in care se desfasoara lupta) + rundele extra
- numar de runde overtime: 3 (6, daca lupta se desfasoara pe teren Woods)

Victima		Modificator
Rogue		-10%
Knight		-20%
Pyromancer	+20%
Wizard		+25%

Pe terenul de tip Woods Rogue-ul primeste 15% bonus damage.

	Mecanism de joc
	
Jocul este bazat pe runde. intr-o runda toate personajele executa cate o miscare (bine determinata), iar daca doua ajung in aceeasi locatie, se lupta.
Mutarea caracterelor se executa simultan; asadar, doi eroi se presupun a fi ajuns in aceeasi locatie doar daca ei se afla in acelasi loc pe harta,
dupa executarea miscarii tuturor eroilor.
O lupta functioneaza astfel:
- fiecare erou isi va calcula parametrii propriilor abilitati, in functie de nivelul lor si de terenul pe care se afla;
- apoi fiecare abilitate va fi modificata in functie de victima;
- dupa aplicarea abilitatilor, eroii isi vor calcula noile HP si XP dupa caz (XP-ul se va modifica doar daca eroul a castigat, respectiv daca si-a omorat adversarul);
- runda se incheie.
In cazul in care doi eroi se intalnesc pe aceeasi pozitie, se calculeaza in primul rand damage-ul primit de ce doi de la abilitatile overtime care ii afecteaza.
Daca unul din ei moare din cauza unei abilitati overtime, lupta nu va mai avea loc.

	Mentiuni
- Harta nu este circulara si nu vor fi cazuri in care eroul sa fie nevoit sa iasa din harta.
- Nu vor exista cazuri in care sa se lupte mai mult de doua personaje in acelasi loc.
- Incapacitatea inseamna doar imposibilitatea de miscare. Eroii aflati sub influenta incapacitatii pot in continuare sa isi foloseasca abilitatile.
- La abilitatile cu efect prelungit (pe mai multe runde), daca un personaj se afla deja sub efectul unei abilitati prelungite
si sufera de la o noua abilitate cu efect prelungit, noul efect il va inlocui pe cel vechi. De asemenea, damage-ul unei abilitati overtime
este calculat in functie de level-ul si terenul unde a avut loc batalia si nu se modifica daca cel afectat se muta pe un alt tip de teren sau daca adversarul face level up.
- Un campion nu primeste experienta daca omoara un adversar folosind o abilitate cu efect prelungit.
- Toate abilitatile se dau simultan, iar acestea tin cont de parametrii initiali (de la inceputul luptei) ai eroilor (HP, level).

Vom citi configuratia si desfasurarea unui joc dintr-un fisier de intrare, vom rula jocul si vom scrie intr-un fisier de iesire starile eroilor.

	Input
Pe prima linie a fisierului se afla 2 numere N si M reprezentand dimensiunile terenului.
Pe urmatoarele N linii se vor gasi N string-uri de lungime M fiecare, cu litere corespunzatoare numelor terenurilor (W,L,V,D).
Pe urmatoarea linie se va gasi un numar P reprezentand numarul de personaje.
Pe urmatoarele P linii se vor gasi cate un string reprezentand rasa fiecarui personaj si pozitia lui initiala
(e.g. “W 0 1” inseamna un Wizard pozitionat pe randul 0 coloana 1).
Pe urmatoarea linie se gaseste R, numarul de runde. Fiecare runda e descrisa de P litere, indicand directia in care se misca fiecare personaj.
Acestea pot lua una din urmatoarele valori:
-	'U'(up ⇔ rand–)
-	'D'(down ⇔ rand++)
-	'L'(left ⇔ col–)
-	'R'(right ⇔ col++)
-	'_' (fara miscare)
Directiile sunt atribuite eroilor in ordinea in care au fost descrise personajele in fisierul de intrare.

	Output
in fisierul de iesire veti adauga P linii in formatul rasa_pers level_pers xp_pers hp_pers row col,
cate una pentru fiecare erou, in ordinea in care au fost “declarati” initial in fisierul de intrare.
Daca personajul este mort, in loc de statistici, pe linia corespunzatoare lui, puneti “dead” (e.g. K dead inseamna un Knight mort).

	Exemplu input/output
	
Exemplu Input:
1 1
L
2
W 0 0
R 0 0
2
__
__

Output asteptat:
W dead
R 0 200 340 0 0
