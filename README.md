**"A labor célja**

Objektumorientált alkalmazás készítése UML (Unified Modeling Language)leírással, C++-ban (vagy Javaban) megvalósítva, iteratív és inkrementálisfolyamat alapján. A hallgatók 5 fős csoportokban dolgoznak és készítik el adokumentumokat a megadott ütemezés  szerint. A dokumentumokat azütemezésben megadott formátumban, általában nyomtatott változatban kell beadni. \[...\] A kiadott feladatot három lépcsőben kell megvalósítani.

· szkeleton

· prototípus

· grafikus

A szkeleton változat célja annak bizonyítása, hogy az objektum és dinamikusmodellek a definiált feladat egy modelljét alkotják. A szkeleton egy program,amelyben már valamennyi, a végső rendszerben is szereplő business objektumszerepel. Az objektumoknak csak az interfésze definiált. Valamennyi metódus az indulás pillanatában az ernyőre szöveges változatban kiírja a saját nevét, majd meghívja azon metódusokat, amelyeket a szolgáltatás végrehajtása érdekében meg kell hívnia. Amennyiben a metódusból valamely feltétel fennállása esetén hívunk meg más metódusokat, akkor a feltételre vonatkozó kérdést interaktívan az ernyőn fel kell tenni és a kapott válasz alapján kell a továbbiakban eljárni. A szkeletonnak alkalmasnak kell lenni arra, hogy a különböző forgatókönyvek és szekvencia diagramok ellenőrizhetők legyenek. Csak karakteres ernyőkezelés fogadható el, mert ez biztosítja a rendszer egyszerűségét.

A prototípusprogram célja annak demonstrálása, hogy a program elkészült, helyesen működik, valamennyi feladatát teljesíti. A prototípus változat egy elkészült program kivéve a kifejlett grafikus interfészt. Ez a program is parancssorbólfuttatható és karakteres ernyőkezelést alkalmaz. A változat tervezésiszempontból elkészült, az ütemezés, az aktív objektumok kezelése megoldott. A business objektumok - a megjelenítésre vonatkozó részeket kivéve - valamennyi metódusa a végleges algoritmusokat tartalmazza. A megjelenítés és működtetés egy alfanumerikus ernyőn vezérelhető és követhető, ugyanakkor a vezérlés fájlból is történhet és a megjelenítés fájlba is logolható, ezzel megteremtve a rendszer tesztelésének lehetőségét. Különös figyelmet kell fordítani a parancssori interfész logikájára, felépítésére, valamint arra, hogy az mennyiben tükrözi és teszi láthatóvá a program működését, a beavatkozások hatásait.

A teljes (grafikus)változat a prototípustól elvileg csak a kezelői felület minőségébenkülönbözhet. Ennek változatnak az értékelésekor a hangsúlyt sokkal inkább amegvalósítás belső szerkezetére, semmint a külalakra kell helyezni.

Az ütemtervezen három-lépcsős felbontásnak megfelelően az adott határidőre elkészítendő feladatokat jelöli ki."

**Feladat leírása:**

A MűegyetemKözponti épületének alagsora alatt egy elátkozott labirintus rejtőzik. Amérnökhallgatók dolga fellelni a Logarléc nevű mágikus képességű ereklyét. A labirintus szobáit ajtók választják el egymástól, ezeken átlépve lehet az egyikszobából a másikba átjutni. Egy-egy szobából legalább egy, de esetenként sokmásik szobába is nyílhat ajtó. Vannak ráadásul ajtók, amelyek csak egy irányban használhatók.

A szobákban különféle tárgyaklehetnek (ilyen a Logarléc is), amiket a hallgatók magukhoz vehetnek, de egyhallgatónál egy időben legfeljebb öt tárgy lehet. A tárgyakat a hallgatók le istudják tenni.

A labirintusban oktatók próbáljákmegakadályozni a hallgatókat abban, hogy sikerrel járjanak. Ha egy oktató egyszobába kerül egy vagy több hallgatóval, akkor elveszi a lelkét és a hallgatókibukik az egyetemről. A tárgyak között azonban vannak olyanok, amik adottideig védettséget nyújtanak az oktatók ellen (pl. a TVSZ denevérbőrrenyomtatott példányai három alkalommal mentik meg a hallgató életét, utánaelveszítik a varázserejüket, a szent söröspoharak pedig csak adott ideighatnak). Van olyan tárgy is, a nedves táblatörlő rongy, amely adott ideigműködik (amíg ki nem szárad), és a vele egy szobában lévő oktatókat megbénítja. A dobozolt káposztás camembert felbontáskor mérges gázt bocsát ki (lásd lejjebb a gázos szobákat). A tárgyakat az oktatók is fel tudják venni!

A szobákban elvétve tranzisztorokis találhatók. A hallgatónál levő tranzisztorokat páronként össze lehetkapcsolni, majd a pár egyik tagját menet közben egy másik szobában le lehettenni. Az így összekapcsolt tranzisztorok varázserővel bírnak: ha a hallgató anála maradó tranzisztort bekapcsolja és leteszi, akkor a másik tranzisztorszobájába kerül, a bekapcsolt tranzisztor pedig kikapcsol. A tranzisztorokkorlátlan ideig használhatók.

Minden szobának van egy (a szobárajellemző) befogadóképessége. Ennél több hallgató és oktató a szobában nemtartózkodhat. Ezen kívül a szobáknak több fajtája is ismert. Vannak szobák,amikben mérgező gáz van. Az ide belépő hallgatók és oktatók egy rövid időreeszméletüket vesztik és a náluk lévő tárgyakat elejtik. Ha valakinél vanFFP2-es maszk, akkor ezekben a szobákban adott időre védettséget kap, de amaszk egyre rövidebb ideig képes a védelem nyújtására. Vannak olyan elátkozott szobák, amiknek az ajtajai időnként eltűnnek, majd később újra előtűnnek.

A szobák egy korábbi(félresikerült) gráfelméleti tételbizonyítás eredményeként meghazudtolják afizika törvényeit: képesek egyesülni és osztódni. Két szomszédos szobaegyesülésével létrejövő szoba a korábbi két szoba tulajdonságaival ésszomszédaival rendelelkezik, de a befogadóképessége a nagyobb szobabefogadóképességével lesz azonos. Az osztódó szoba két olyan szobára válikszét, amelyek egymás szomszédai lesznek, és megosztoznak a korábbi szobaképességein és szomszédain (a korábbi szomszédok, vagy csak az egyik, vagy csak a másik “új” szobának lesznek szomszédai).

A játékot egyszerre több játékos játssza, akik ahallgatókat irányítják, és akkor nyernek, ha megadott időn belül megtalálták ésmagukhoz vették a Logarlécet."**"Módosítás**

· A söröskorsó képessége (véd az oktatótól)kibővül: a még aktív söröskorsót használva a hallgatók elejtik az egyik náluklevő tárgyat.

· Új tárgy: légfrissítő. Egyszerhasználatos tárgy.Gázos szobában lerakva semlegesíti a gázhatást.

· Új ember: takarító. A szobák befogadóképessége ráis érvényes. Ha belép egy szobába, minden mozogni képes (nem bénult / ájult) embert kitessékel onnan. Ha gázos szobába lép, kiszellőztet, megszüntetve a szoba gázosságát. A szobák a takarítást követően adott számú látogató után ragacsossá válnak: a bennük lévő és bennük letett tárgyakat nem lehet felvenni. 

· Egyes tárgyaknak (tvsz, maszk, logarléc) létezik"hamis" változata, amelyiknek nincs az eredeti tárgyra jellemző jótulajdonsága. Például a hamis logarléc felvételével nem lehet nyerni."