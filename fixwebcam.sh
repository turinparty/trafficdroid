#!/bin/sh

FILES="126.xml 131.xml 134.xml 13442.xml 13978.xml 140.xml 141.xml 142.xml 14230.xml 143.xml 144.xml 145.xml 146.xml 147.xml 150.xml 151.xml 156.xml 157.xml 158.xml 159.xml 160.xml 163.xml 164.xml 165.xml 166.xml 167.xml 168.xml 169.xml 170.xml 171.xml 172.xml 173.xml 174.xml 175.xml 181.xml 183.xml 184.xml 187.xml 189.xml 190.xml 191.xml 192.xml 193.xml 194.xml 195.xml 196.xml 197.xml 198.xml 200.xml 202.xml 203.xml 204.xml 206.xml 207.xml 211.xml 213.xml 214.xml 217.xml 219.xml 221.xml 222.xml 223.xml 228.xml 231.xml 236.xml 238.xml 239.xml 240.xml 241.xml 242.xml 243.xml 247.xml 260.xml 263.xml 264.xml 266.xml 270.xml 273.xml 281.xml 283.xml 292.xml 513.xml 516.xml 533.xml 604.xml 613.xml 621.xml 703.xml 717.xml 718.xml 922.xml 936.xml"

for FILE in $FILES
do

perl -pi -e 's|.*D18 km. 4,0 Roma Nord D.T..*|<item>A117</item> <!-- D18 km. 4,0 Roma Nord D.T. NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 5,0 Feronia itinere sud.*|<item>A99</item> <!-- D18 km. 5,0 Feronia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 Km. 09,9 Castelnuovo di Porto itinere sud.*|<item>A2298</item> <!-- D18 Km. 09,9 Castelnuovo di Porto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 17,8 Settebagni itinere sud HD.*|<item>A119</item> <!-- D18 km. 17,8 Settebagni itinere sud HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 22,5 Settebagni itinere nord HD.*|<item>A458</item> <!-- D18 km. 22,5 Settebagni itinere nord HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 23,0 GRA Nord.*|<item>A120</item> <!-- D18 km. 23,0 GRA Nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo Sud.*|<item>A1018</item> <!-- D19 km. 03,8 PMV S.Cesareo Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo itinere nord.*|<item>A1019</item> <!-- D19 km. 03,8 PMV S.Cesareo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 10,2 Itinere.M.Porzio.*|<item>A2007</item> <!-- D19 km. 10,2 Itinere.M.Porzio NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 15 Barriera Roma Sud.*|<item>A121</item> <!-- D19 km. 15 Barriera Roma Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 17,7 PMV Torrenova itinere Sud.*|<item>A450</item> <!-- D19 km. 17,7 PMV Torrenova itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 20,0 Gra Sud HD.*|<item>A275</item> <!-- D19 km. 20,0 Gra Sud HD NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 411,6 Chiusi itinere nord.*|<item>A947</item> <!-- A01 km. 411,6 Chiusi itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 422,0 Bagnaiolo itinere nord.*|<item>A109</item> <!-- A01 km. 422,0 Bagnaiolo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 426,7 Fabro itinere sud.*|<item>A1032</item> <!-- A01 km. 426,7 Fabro itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 429,1 Fabro itinere nord.*|<item>A461</item> <!-- A01 km. 429,1 Fabro itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 439,0 Ritorto itinere nord.*|<item>A103</item> <!-- A01 km. 439,0 Ritorto itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 449,7 Orvieto itinere sud.*|<item>A445</item> <!-- A01 km. 449,7 Orvieto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 460,0 Baschi itinere sud.*|<item>A114</item> <!-- A01 km. 460,0 Baschi itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 467,1 Tevere itinere nord.*|<item>A460</item> <!-- A01 km. 467,1 Tevere itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 478,4 Attigliano itinere sud.*|<item>A1034</item> <!-- A01 km. 478,4 Attigliano itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 483,7 Attigliano itinere nord.*|<item>A1033</item> <!-- A01 km. 483,7 Attigliano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 489,9 Orte itnere sud.*|<item>A446</item> <!-- A01 km. 489,9 Orte itnere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 500,0 Magliano itinere nord.*|<item>A102</item> <!-- A01 km. 500,0 Magliano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 503,1 Magliano itinere nord.*|<item>A1030</item> <!-- A01 km. 503,1 Magliano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 504,0 Borghetto itinere nord.*|<item>A276</item> <!-- A01 km. 504,0 Borghetto itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 506,0 Foglia itinere sud.*|<item>A100</item> <!-- A01 km. 506,0 Foglia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 110,9 LES CRETES N 001.*|<item>A1494</item> <!-- A5 km. 110,9 LES CRETES N 001 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 112,6 LES CRETES S 051.*|<item>A1501</item> <!-- A5 km. 112,6 LES CRETES S 051 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 113,1 SV. AOSTA W 000.*|<item>A1493</item> <!-- A5 km. 113,1 SV. AOSTA W 000 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 113,4 VILLENEUVE N 101.*|<item>A1468</item> <!-- A5 km. 113,4 VILLENEUVE N 101 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 117,0 VILLENEUVE S 151.*|<item>A1481</item> <!-- A5 km. 117,0 VILLENEUVE S 151 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 117,5 ARVIER N 201.*|<item>A1452</item> <!-- A5 km. 117,5 ARVIER N 201 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 120,1 LEVEROGNE N 301.*|<item>A1438</item> <!-- A5 km. 120,1 LEVEROGNE N 301 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 120,3 ARVIER S 251.*|<item>A1460</item> <!-- A5 km. 120,3 ARVIER S 251 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 122,2 AVISE N 401.*|<item>A1416</item> <!-- A5 km. 122,2 AVISE N 401 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 122,2 LEVEROGNE S 351.*|<item>A1446</item> <!-- A5 km. 122,2 LEVEROGNE S 351 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 125,6 AVISE S 451.*|<item>A1427</item> <!-- A5 km. 125,6 AVISE S 451 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 125,6 VILLARET N 501.*|<item>A1393</item> <!-- A5 km. 125,6 VILLARET N 501 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 128,5 CHABODEY N 601.*|<item>A1381</item> <!-- A5 km. 128,5 CHABODEY N 601 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 128,7 VILLARET S 550.*|<item>A1405</item> <!-- A5 km. 128,7 VILLARET S 550 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 129,8 CHABODEY S 651.*|<item>A1387</item> <!-- A5 km. 129,8 CHABODEY S 651 NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 411,7  Lanciano itinere sud.*|<item>A554</item> <!-- A14 km. 411,7  Lanciano itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 416,5 Lanciano itinere nord.*|<item>A555</item> <!-- A14 km. 416,5 Lanciano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 417,1 Val di Sangro itinere sud.*|<item>A1281</item> <!-- A14 km. 417,1 Val di Sangro itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 425,1 Val di Sangro itinere nord.*|<item>A1280</item> <!-- A14 km. 425,1 Val di Sangro itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 428,0 Sangro itinere sud.*|<item>A2018</item> <!-- A14 km. 428,0 Sangro itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 434,4 Vasto Nord itinere sud.*|<item>A1279</item> <!-- A14 km. 434,4 Vasto Nord itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 440,9 Vasto Nord itinere nord.*|<item>A556</item> <!-- A14 km. 440,9 Vasto Nord itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 440,0 Vasto Sud itinere sud.*|<item>A557</item> <!-- A14 km. 440,0 Vasto Sud itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 458,0 Trigno itinere nord.*|<item>A2019</item> <!-- A14 km. 458,0 Trigno itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 468,0 Termoli itinere sud.*|<item>A559</item> <!-- A14 km. 468,0 Termoli itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 468,7 Vasto Sud itinere nord.*|<item>A558</item> <!-- A14 km. 468,7 Vasto Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 481,6 Termoli itinere nord.*|<item>A1251</item> <!-- A14 km. 481,6 Termoli itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 493,5 Torre Fantine itinere nord.*|<item>A2020</item> <!-- A14 km. 493,5 Torre Fantine itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 502,8 Poggio Imp. itinere sud.*|<item>A1300</item> <!-- A14 km. 502,8 Poggio Imp. itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 508,0 Poggio Imperiale itinere nord.*|<item>A1843</item> <!-- A14 km. 508,0 Poggio Imperiale itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 008,6 Barriera Ravenna itinere.*|<item>A948</item> <!-- D14 km. 008,6 Barriera Ravenna itinere NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 011,0 Lugo Cotignola Itinere Nord.*|<item>A332</item> <!-- D14 km. 011,0 Lugo Cotignola Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 024,0 Ravenna ex barriera.*|<item>A50</item> <!-- D14 km. 024,0 Ravenna ex barriera NEW -->|g' $FILE
perl -pi -e 's|.*D14 km 27,0 Ravenna Itinere Nord.*|<item>A2637</item> <!-- D14 km 27,0 Ravenna Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 000,2 PMV Bologna Casalecchio It. Sud.*|<item>A333</item> <!-- R14 km. 000,2 PMV Bologna Casalecchio It. Sud NEW -->|g' $FILE
perl -pi -e 's|.*R01 Km.1,1 Borgo Panigale dir sx.*|<item>A2483</item> <!-- R01 Km.1,1 Borgo Panigale dir sx NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 002,0 Bo Casalecchio Itinere Ovest.*|<item>A334</item> <!-- R14 km. 002,0 Bo Casalecchio Itinere Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A30 km. 1,3 All. A30-A01 itinere nord.*|<item>A530</item> <!-- A30 km. 1,3 All. A30-A01 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A30 km. 17,2 Nola itinere sud.*|<item>A532</item> <!-- A30 km. 17,2 Nola itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A30 km. 32,08 Palma Campania itinere nord.*|<item>A2628</item> <!-- A30 km. 32,08 Palma Campania itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A30 km. 38,3 Sarno itinere nord.*|<item>A2627</item> <!-- A30 km. 38,3 Sarno itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A30 km. 41,0 Nocera Pagani itinere nord.*|<item>A1511</item> <!-- A30 km. 41,0 Nocera Pagani itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A30 km. 42,785 Castel S. Giorgio itinere sud.*|<item>A2585</item> <!-- A30 km. 42,785 Castel S. Giorgio itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A30 km. 47,9 S.Aniello itinere nord.*|<item>A128</item> <!-- A30 km. 47,9 S.Aniello itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A30 km. 49,7 Barriera Salerno.*|<item>A127</item> <!-- A30 km. 49,7 Barriera Salerno NEW -->|g' $FILE
perl -pi -e 's|.*A30 km. 53,7 Mercato S.Severino itinere nord.*|<item>A533</item> <!-- A30 km. 53,7 Mercato S.Severino itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A30 km. 55,0 itinere nord.*|<item>A2136</item> <!-- A30 km. 55,0 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 1,9 A12/A7 itinere ovest.*|<item>A492</item> <!-- A12 km. 1,9 A12/A7 itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 2,0 GE-Est itinere est.*|<item>A470</item> <!-- A12 km. 2,0 GE-Est itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 4,2 Genova Est itinere est.*|<item>A2382</item> <!-- A12 km. 4,2 Genova Est itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 5,4 Ge Est it. Ovest.*|<item>A2345</item> <!-- A12 km. 5,4 Ge Est it. Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 11,2 GE-Nervi itinere est.*|<item>A468</item> <!-- A12 km. 11,2 GE-Nervi itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 12,0 GE-Nervi itinere est.*|<item>A2</item> <!-- A12 km. 12,0 GE-Nervi itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 12,4 GE-Nervi itinere ovest.*|<item>A469</item> <!-- A12 km. 12,4 GE-Nervi itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 20,5 Recco itinere est.*|<item>A493</item> <!-- A12 km. 20,5 Recco itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 23,0 Recco itinere ovest.*|<item>A11</item> <!-- A12 km. 23,0 Recco itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 24,2 Recco itinere ovest.*|<item>A1052</item> <!-- A12 km. 24,2 Recco itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 28,0 Rapallo itinere ovest.*|<item>A14</item> <!-- A12 km. 28,0 Rapallo itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 29,6 Rapallo itinere ovest.*|<item>A467</item> <!-- A12 km. 29,6 Rapallo itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 34,3 Chiavari itinere est.*|<item>A495</item> <!-- A12 km. 34,3 Chiavari itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 39,0 Chiavari itinere Est.*|<item>A480</item> <!-- A12 km. 39,0 Chiavari itinere Est NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 39,7 Chiavari itinere ovest.*|<item>A494</item> <!-- A12 km. 39,7 Chiavari itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 0,0 itinere nord.*|<item>A2110</item> <!-- A12 km. 0,0 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 09,9 Maccarese itinere nord.*|<item>A465</item> <!-- A12 km. 09,9 Maccarese itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 15,0 Torrimpietra Tower itinere nord.*|<item>A2008</item> <!-- A12 km. 15,0 Torrimpietra Tower itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 30,0 Cerveteri itinere sud.*|<item>A462</item> <!-- A12 km. 30,0 Cerveteri itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 39,5 Tirreno Est itinere nord.*|<item>A2081</item> <!-- A12 km. 39,5 Tirreno Est itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 50,2 Aurelia itinere nord.*|<item>A464</item> <!-- A12 km. 50,2 Aurelia itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 53,0 Aurelia itinere sud.*|<item>A463</item> <!-- A12 km. 53,0 Aurelia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 65,0 Svincolo Tarquinia itinere sud.*|<item>A2000</item> <!-- A12 km. 65,0 Svincolo Tarquinia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 22,0 itinere sud.*|<item>A1551</item> <!-- A23 km. 22,0 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 25,2 Udine Nord itinere nord.*|<item>A542</item> <!-- A23 km. 25,2 Udine Nord itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 31,0  Udine Nord itinere sud.*|<item>A543</item> <!-- A23 km. 31,0  Udine Nord itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A23 km 33,6 Cormor Est itinere sud.*|<item>A1240</item> <!-- A23 km 33,6 Cormor Est itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 37,0 Ledra Ovest itinere sud.*|<item>A1845</item> <!-- A23 km. 37,0 Ledra Ovest itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 42,0 Gemona itinere nord.*|<item>A1877</item> <!-- A23 km. 42,0 Gemona itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 47,0 Gemona itinere sud.*|<item>A1876</item> <!-- A23 km. 47,0 Gemona itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 54,3 Carnia itinere nord.*|<item>A544</item> <!-- A23 km. 54,3 Carnia itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 56,1 Viadotto Somplago itinere nord.*|<item>A321</item> <!-- A23 km. 56,1 Viadotto Somplago itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 61,0 Carnia itinere sud.*|<item>A545</item> <!-- A23 km. 61,0 Carnia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 72,6 Resiutta itinere sud.*|<item>A816</item> <!-- A23 km. 72,6 Resiutta itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 77,0 Chiusaforte itin. nord.*|<item>A1227</item> <!-- A23 km. 77,0 Chiusaforte itin. nord NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 85,1 Viad. Fella V itin. sud.*|<item>A1244</item> <!-- A23 km. 85,1 Viad. Fella V itin. sud NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 87,2 Viad. Fella VI itinere sud.*|<item>A1243</item> <!-- A23 km. 87,2 Viad. Fella VI itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A23 km. 89,6 Pontebba itinere nord.*|<item>A546</item> <!-- A23 km. 89,6 Pontebba itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 1,0 Casalecchio itinere nord.*|<item>A53</item> <!-- A14 km. 1,0 Casalecchio itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 3,0 Borgo Panigale itinere sud.*|<item>A336</item> <!-- A14 km. 3,0 Borgo Panigale itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 4,8 Borgo Panigale itiner sud.*|<item>A59</item> <!-- A14 km. 4,8 Borgo Panigale itiner sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 5,5 Borgo Panigale itinere nord.*|<item>A337</item> <!-- A14 km. 5,5 Borgo Panigale itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 9 HD Bivio A14-Racc. Casalecchio it.sud.*|<item>A57</item> <!-- A14 km. 9 HD Bivio A14-Racc. Casalecchio it.sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 009,5 Itinere Nord.*|<item>A2048</item> <!-- A14 km. 009,5 Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 009,5 Itinere Sud.*|<item>A2047</item> <!-- A14 km. 009,5 Itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 009,5 Nord.*|<item>A1894</item> <!-- A14 km. 009,5 Nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 11,5 SH12 Nord.*|<item>A2042</item> <!-- A14 km. 11,5 SH12 Nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 012,6 Itinere Sud.*|<item>A2035</item> <!-- A14 km. 012,6 Itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 13,5 SH18 Nord.*|<item>A2045</item> <!-- A14 km. 13,5 SH18 Nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 13,5 SH18 Sud.*|<item>A2046</item> <!-- A14 km. 13,5 SH18 Sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 14,7 SH22 Nord.*|<item>A2040</item> <!-- A14 km. 14,7 SH22 Nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 14,7 SH22 Sud.*|<item>A2033</item> <!-- A14 km. 14,7 SH22 Sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 16 Bologna Fiera itinere nord.*|<item>A621</item> <!-- A14 km. 16 Bologna Fiera itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 008,6 Barriera Ravenna itinere.*|<item>A948</item> <!-- D14 km. 008,6 Barriera Ravenna itinere NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 011,0 Lugo Cotignola Itinere Nord.*|<item>A332</item> <!-- D14 km. 011,0 Lugo Cotignola Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 024,0 Ravenna ex barriera.*|<item>A50</item> <!-- D14 km. 024,0 Ravenna ex barriera NEW -->|g' $FILE
perl -pi -e 's|.*D14 km 27,0 Ravenna Itinere Nord.*|<item>A2637</item> <!-- D14 km 27,0 Ravenna Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 000,2 PMV Bologna Casalecchio It. Sud.*|<item>A333</item> <!-- R14 km. 000,2 PMV Bologna Casalecchio It. Sud NEW -->|g' $FILE
perl -pi -e 's|.*R01 Km.1,1 Borgo Panigale dir sx.*|<item>A2483</item> <!-- R01 Km.1,1 Borgo Panigale dir sx NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 002,0 Bo Casalecchio Itinere Ovest.*|<item>A334</item> <!-- R14 km. 002,0 Bo Casalecchio Itinere Ovest NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 4,0 Roma Nord D.T..*|<item>A117</item> <!-- D18 km. 4,0 Roma Nord D.T. NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 5,0 Feronia itinere sud.*|<item>A99</item> <!-- D18 km. 5,0 Feronia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 Km. 09,9 Castelnuovo di Porto itinere sud.*|<item>A2298</item> <!-- D18 Km. 09,9 Castelnuovo di Porto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 17,8 Settebagni itinere sud HD.*|<item>A119</item> <!-- D18 km. 17,8 Settebagni itinere sud HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 22,5 Settebagni itinere nord HD.*|<item>A458</item> <!-- D18 km. 22,5 Settebagni itinere nord HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 23,0 GRA Nord.*|<item>A120</item> <!-- D18 km. 23,0 GRA Nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo Sud.*|<item>A1018</item> <!-- D19 km. 03,8 PMV S.Cesareo Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo itinere nord.*|<item>A1019</item> <!-- D19 km. 03,8 PMV S.Cesareo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 10,2 Itinere.M.Porzio.*|<item>A2007</item> <!-- D19 km. 10,2 Itinere.M.Porzio NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 15 Barriera Roma Sud.*|<item>A121</item> <!-- D19 km. 15 Barriera Roma Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 17,7 PMV Torrenova itinere Sud.*|<item>A450</item> <!-- D19 km. 17,7 PMV Torrenova itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 20,0 Gra Sud HD.*|<item>A275</item> <!-- D19 km. 20,0 Gra Sud HD NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 109,2 PMV Parma Itinere Sud.*|<item>A410</item> <!-- A01 km. 109,2 PMV Parma Itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A01  km. 110,8 Parma.*|<item>A29</item> <!-- A01  km. 110,8 Parma NEW -->|g' $FILE
perl -pi -e 's|.*A01  km. 113,2 PMV Parma Itinere Nord.*|<item>A419</item> <!-- A01  km. 113,2 PMV Parma Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 114,6 Ads S.Martino.*|<item>A2082</item> <!-- A01 km. 114,6 Ads S.Martino NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 122,4 Campegine itinere sud.*|<item>A2782</item> <!-- A01 km. 122,4 Campegine itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 125,9 Campegine itinere nord.*|<item>A2654</item> <!-- A01 km. 125,9 Campegine itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 126,0 itinere sud.*|<item>A648</item> <!-- A01 km. 126,0 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 135,0 Crostolo itinere sud.*|<item>A365</item> <!-- A01 km. 135,0 Crostolo itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 139,0 Reggio Emilia itinere nord.*|<item>A366</item> <!-- A01 km. 139,0 Reggio Emilia itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 153,0 Calvetro itinere sud.*|<item>A367</item> <!-- A01 km. 153,0 Calvetro itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 155,0 A1/A22 itinere Nord.*|<item>A64</item> <!-- A01 km. 155,0 A1/A22 itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 156,0 Secchia itinere nord.*|<item>A65</item> <!-- A01 km. 156,0 Secchia itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 157,0 Modena Nord itinere sud.*|<item>A368</item> <!-- A01 km. 157,0 Modena Nord itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 158,0 Modena Nord itinere nord.*|<item>A369</item> <!-- A01 km. 158,0 Modena Nord itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 162,0 itinere sud.*|<item>A579</item> <!-- A01 km. 162,0 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 127,0 Rimini Sud itinere Sud.*|<item>A61</item> <!-- A14 km. 127,0 Rimini Sud itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 129,0 Rimini Sud itinere nord.*|<item>A914</item> <!-- A14 km. 129,0 Rimini Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 134,2 Riccione itinere sud.*|<item>A911</item> <!-- A14 km. 134,2 Riccione itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 143,0 Cattolica itinere sud.*|<item>A349</item> <!-- A14 km. 143,0 Cattolica itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 145,9 Cattolica itinere nord.*|<item>A1301</item> <!-- A14 km. 145,9 Cattolica itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 152,0 Cattolica itinere nord.*|<item>A1265</item> <!-- A14 km. 152,0 Cattolica itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 154,8 Case Bruciate itinere sud.*|<item>A137</item> <!-- A14 km. 154,8 Case Bruciate itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 155,0 Pesaro itinere sud.*|<item>A295</item> <!-- A14 km. 155,0 Pesaro itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 162,0 Colombaraccia itinere sud.*|<item>A138</item> <!-- A14 km. 162,0 Colombaraccia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 169,2 Fano itinere sud.*|<item>A297</item> <!-- A14 km. 169,2 Fano itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 176,2 Fano itinere nord.*|<item>A1233</item> <!-- A14 km. 176,2 Fano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 182,7 Marotta itinere sud.*|<item>A1245</item> <!-- A14 km. 182,7 Marotta itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 189,0 Senigallia itinere sud.*|<item>A298</item> <!-- A14 km. 189,0 Senigallia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 189,1 Marotta itinere nord.*|<item>A299</item> <!-- A14 km. 189,1 Marotta itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 223,0 Galleria Sappanico itinere nord.*|<item>A141</item> <!-- A14 km. 223,0 Galleria Sappanico itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 008,6 Barriera Ravenna itinere.*|<item>A948</item> <!-- D14 km. 008,6 Barriera Ravenna itinere NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 011,0 Lugo Cotignola Itinere Nord.*|<item>A332</item> <!-- D14 km. 011,0 Lugo Cotignola Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 024,0 Ravenna ex barriera.*|<item>A50</item> <!-- D14 km. 024,0 Ravenna ex barriera NEW -->|g' $FILE
perl -pi -e 's|.*D14 km 27,0 Ravenna Itinere Nord.*|<item>A2637</item> <!-- D14 km 27,0 Ravenna Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 000,2 PMV Bologna Casalecchio It. Sud.*|<item>A333</item> <!-- R14 km. 000,2 PMV Bologna Casalecchio It. Sud NEW -->|g' $FILE
perl -pi -e 's|.*R01 Km.1,1 Borgo Panigale dir sx.*|<item>A2483</item> <!-- R01 Km.1,1 Borgo Panigale dir sx NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 002,0 Bo Casalecchio Itinere Ovest.*|<item>A334</item> <!-- R14 km. 002,0 Bo Casalecchio Itinere Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A15 km. 1,0 A15/A1 itinere nord.*|<item>A418</item> <!-- A15 km. 1,0 A15/A1 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 151,6 Cavenago itinere ovest.*|<item>A174</item> <!-- A04 km. 151,6 Cavenago itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 154,9 Trezzo itinere est.*|<item>A423</item> <!-- A04 km. 154,9 Trezzo itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 158,1 Trezzo itinere ovest.*|<item>A438</item> <!-- A04 km. 158,1 Trezzo itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 159,7 Capriate itinere est.*|<item>A1869</item> <!-- A04 km. 159,7 Capriate itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 161,6 Capriate itinere ovest.*|<item>A439</item> <!-- A04 km. 161,6 Capriate itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 162,2 Ponte Brembo itinere ovest.*|<item>A45</item> <!-- A04 km. 162,2 Ponte Brembo itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 166,6 Dalmine itinere est.*|<item>A425</item> <!-- A04 km. 166,6 Dalmine itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 168,8 Dalmine itinere ovest.*|<item>A440</item> <!-- A04 km. 168,8 Dalmine itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 171,2 Bergamo itinere est.*|<item>A426</item> <!-- A04 km. 171,2 Bergamo itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 172,6 Bergamo itinere ovest.*|<item>A40</item> <!-- A04 km. 172,6 Bergamo itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 174,3 Bergamo itinere ovest.*|<item>A2387</item> <!-- A04 km. 174,3 Bergamo itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 178,4 Seriate itinere est.*|<item>A427</item> <!-- A04 km. 178,4 Seriate itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 180,2 Seriate itinere ovest.*|<item>A175</item> <!-- A04 km. 180,2 Seriate itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 187,2 Grumello itinere est.*|<item>A428</item> <!-- A04 km. 187,2 Grumello itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 189,0 Grumello itinere ovest.*|<item>A176</item> <!-- A04 km. 189,0 Grumello itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 4,0 Roma Nord D.T..*|<item>A117</item> <!-- D18 km. 4,0 Roma Nord D.T. NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 5,0 Feronia itinere sud.*|<item>A99</item> <!-- D18 km. 5,0 Feronia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 Km. 09,9 Castelnuovo di Porto itinere sud.*|<item>A2298</item> <!-- D18 Km. 09,9 Castelnuovo di Porto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 17,8 Settebagni itinere sud HD.*|<item>A119</item> <!-- D18 km. 17,8 Settebagni itinere sud HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 22,5 Settebagni itinere nord HD.*|<item>A458</item> <!-- D18 km. 22,5 Settebagni itinere nord HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 23,0 GRA Nord.*|<item>A120</item> <!-- D18 km. 23,0 GRA Nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo Sud.*|<item>A1018</item> <!-- D19 km. 03,8 PMV S.Cesareo Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo itinere nord.*|<item>A1019</item> <!-- D19 km. 03,8 PMV S.Cesareo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 10,2 Itinere.M.Porzio.*|<item>A2007</item> <!-- D19 km. 10,2 Itinere.M.Porzio NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 15 Barriera Roma Sud.*|<item>A121</item> <!-- D19 km. 15 Barriera Roma Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 17,7 PMV Torrenova itinere Sud.*|<item>A450</item> <!-- D19 km. 17,7 PMV Torrenova itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 20,0 Gra Sud HD.*|<item>A275</item> <!-- D19 km. 20,0 Gra Sud HD NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 574,6 itinere sud.*|<item>A449</item> <!-- A01 km. 574,6 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 577,0 Bivio A01-Diramazione Roma Sud itinere sud.*|<item>A274</item> <!-- A01 km. 577,0 Bivio A01-Diramazione Roma Sud itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 580,0 A01-Diramazione Roma Sud itinere nord.*|<item>A122</item> <!-- A01 km. 580,0 A01-Diramazione Roma Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 584,3 Valmontone itinere sud.*|<item>A1020</item> <!-- A01 km. 584,3 Valmontone itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 586,5 Valmontone itinere nord.*|<item>A535</item> <!-- A01 km. 586,5 Valmontone itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 589,2 Valmontone itinere nord.*|<item>A1026</item> <!-- A01 km. 589,2 Valmontone itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 589,3 Colleferro itinere sud.*|<item>A1021</item> <!-- A01 km. 589,3 Colleferro itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 593,0 Colleferro itinere sud.*|<item>A123</item> <!-- A01 km. 593,0 Colleferro itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 596,0 Colleferro itinere nord.*|<item>A1022</item> <!-- A01 km. 596,0 Colleferro itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 602,9 Anagni itinere sud.*|<item>A451</item> <!-- A01 km. 602,9 Anagni itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 605,7 Anagni itinere nord.*|<item>A454</item> <!-- A01 km. 605,7 Anagni itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 611,0 La Macchia itinere Nord.*|<item>A1239</item> <!-- A01 km. 611,0 La Macchia itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 622,5 Frosinone itinere sud.*|<item>A452</item> <!-- A01 km. 622,5 Frosinone itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 624,0 Frosinone itinere nord.*|<item>A115</item> <!-- A01 km. 624,0 Frosinone itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 624,5 Frosinone itinere nord.*|<item>A453</item> <!-- A01 km. 624,5 Frosinone itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 0,250 Bivio A26S/A10E itinere est.*|<item>A6</item> <!-- A26 km. 0,250 Bivio A26S/A10E itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 4,9 Gall.Monacchi Itinere sud.*|<item>A2578</item> <!-- A26 km. 4,9 Gall.Monacchi Itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 5,0 Casa della Volpe itinere nord.*|<item>A2550</item> <!-- A26 km. 5,0 Casa della Volpe itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 6,0 Turchino itinere sud.*|<item>A475</item> <!-- A26 km. 6,0 Turchino itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 7,3 Turchino Itinere nord.*|<item>A497</item> <!-- A26 km. 7,3 Turchino Itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 10,0 Turchino Fado itinere sud.*|<item>A1037</item> <!-- A26 km. 10,0 Turchino Fado itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 14,0 Masone itinere sud.*|<item>A741</item> <!-- A26 km. 14,0 Masone itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 16,2 Masone itinere sud.*|<item>A916</item> <!-- A26 km. 16,2 Masone itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 19,7 Anzema itinere sud.*|<item>A1993</item> <!-- A26 km. 19,7 Anzema itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 21,6 Broglio itinere nord.*|<item>A2474</item> <!-- A26 km. 21,6 Broglio itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 25,0 Stura itinere sud.*|<item>A740</item> <!-- A26 km. 25,0 Stura itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 28,2 Betulle itinere sud.*|<item>A2520</item> <!-- A26 km. 28,2 Betulle itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 28,9 Ovada itinere nord.*|<item>A739</item> <!-- A26 km. 28,9 Ovada itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 29,8 Ovada itinere sud.*|<item>A738</item> <!-- A26 km. 29,8 Ovada itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 35,2 itinere sud.*|<item>A1994</item> <!-- A26 km. 35,2 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D26 km. 1,4 D26/A26 itinere ovest.*|<item>A500</item> <!-- D26 km. 1,4 D26/A26 itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*D26 km. 8,0 Novi Ligure itinere est.*|<item>A2385</item> <!-- D26 km. 8,0 Novi Ligure itinere est NEW -->|g' $FILE
perl -pi -e 's|.*D36 km. 8,6 Vercelli Ovest itinere Est.*|<item>A505</item> <!-- D36 km. 8,6 Vercelli Ovest itinere Est NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 525,0 San Severo itinere sud.*|<item>A1532</item> <!-- A14 km. 525,0 San Severo itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 542,1 PMV Gargano itinere sud.*|<item>A560</item> <!-- A14 km. 542,1 PMV Gargano itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 580,0 Saline itinere sud.*|<item>A1549</item> <!-- A14 km. 580,0 Saline itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 592,4 itinere nord.*|<item>A2555</item> <!-- A14 km. 592,4 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 599,5 San Ferdinando itinere sud.*|<item>A2372</item> <!-- A14 km. 599,5 San Ferdinando itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 608,810 itinere sud.*|<item>A2446</item> <!-- A14 km. 608,810 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 611,1 Canosa itinere nord.*|<item>A563</item> <!-- A14 km. 611,1 Canosa itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 628,8 Andria-Barletta itinere nord.*|<item>A1885</item> <!-- A14 km. 628,8 Andria-Barletta itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 637,1 Trani itinere sud.*|<item>A1882</item> <!-- A14 km. 637,1 Trani itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 642,5 Trani itinere sud.*|<item>A565</item> <!-- A14 km. 642,5 Trani itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 648,2 Trani itinere nord.*|<item>A566</item> <!-- A14 km. 648,2 Trani itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 654,0 Molfetta itinere nord.*|<item>A1883</item> <!-- A14 km. 654,0 Molfetta itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 660,2 Bitonto itinere sud.*|<item>A2321</item> <!-- A14 km. 660,2 Bitonto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 664,0 Bitonto itinere nord.*|<item>A567</item> <!-- A14 km. 664,0 Bitonto itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 676,7 itinere sud.*|<item>A2164</item> <!-- A14 km. 676,7 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 008,6 Barriera Ravenna itinere.*|<item>A948</item> <!-- D14 km. 008,6 Barriera Ravenna itinere NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 011,0 Lugo Cotignola Itinere Nord.*|<item>A332</item> <!-- D14 km. 011,0 Lugo Cotignola Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 024,0 Ravenna ex barriera.*|<item>A50</item> <!-- D14 km. 024,0 Ravenna ex barriera NEW -->|g' $FILE
perl -pi -e 's|.*D14 km 27,0 Ravenna Itinere Nord.*|<item>A2637</item> <!-- D14 km 27,0 Ravenna Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 000,2 PMV Bologna Casalecchio It. Sud.*|<item>A333</item> <!-- R14 km. 000,2 PMV Bologna Casalecchio It. Sud NEW -->|g' $FILE
perl -pi -e 's|.*R01 Km.1,1 Borgo Panigale dir sx.*|<item>A2483</item> <!-- R01 Km.1,1 Borgo Panigale dir sx NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 002,0 Bo Casalecchio Itinere Ovest.*|<item>A334</item> <!-- R14 km. 002,0 Bo Casalecchio Itinere Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 0,1 Inizio Competenza itinere sud.*|<item>A327</item> <!-- A27 km. 0,1 Inizio Competenza itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 2,5 Mogliano Veneto itinere nord.*|<item>A322</item> <!-- A27 km. 2,5 Mogliano Veneto itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 10,2 Treviso Sud itinere nord.*|<item>A538</item> <!-- A27 km. 10,2 Treviso Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 21,5 Treviso Nord itinere nord.*|<item>A2593</item> <!-- A27 km. 21,5 Treviso Nord itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 26,0 Treviso Nord itinere sud.*|<item>A539</item> <!-- A27 km. 26,0 Treviso Nord itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 40,0 Conegliano itinere nord.*|<item>A1896</item> <!-- A27 km. 40,0 Conegliano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 41,2 Svinc. Conegliano lato VE.*|<item>A817</item> <!-- A27 km. 41,2 Svinc. Conegliano lato VE NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 43,0 Conegliano itinere sud.*|<item>A1897</item> <!-- A27 km. 43,0 Conegliano itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 51,0 Vittorio V. Sud itinere nord.*|<item>A1899</item> <!-- A27 km. 51,0 Vittorio V. Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 54,1 Viadotto Ruio itinere nord.*|<item>A838</item> <!-- A27 km. 54,1 Viadotto Ruio itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 57,0 Gall. Monte Baldo itinere sud.*|<item>A837</item> <!-- A27 km. 57,0 Gall. Monte Baldo itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 58,4 Vittorio V. Nord itinere nord.*|<item>A1273</item> <!-- A27 km. 58,4 Vittorio V. Nord itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 62,0 Fadalto itinere nord.*|<item>A1898</item> <!-- A27 km. 62,0 Fadalto itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 62,8 Vittorio V. Nord itinere sud.*|<item>A943</item> <!-- A27 km. 62,8 Vittorio V. Nord itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 069,1 G. cave ovest dome.*|<item>A1075</item> <!-- A27 km. 069,1 G. cave ovest dome NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 069,6 PMV Cave est itin. nord.*|<item>A666</item> <!-- A27 km. 069,6 PMV Cave est itin. nord NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 073,1 G.Cave est dome est. edificio 1.*|<item>A1133</item> <!-- A27 km. 073,1 G.Cave est dome est. edificio 1 NEW -->|g' $FILE
perl -pi -e 's|.*A27 km. 81,9 Allacc. SS.51 itinere nord.*|<item>A331</item> <!-- A27 km. 81,9 Allacc. SS.51 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 4,0 Roma Nord D.T..*|<item>A117</item> <!-- D18 km. 4,0 Roma Nord D.T. NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 5,0 Feronia itinere sud.*|<item>A99</item> <!-- D18 km. 5,0 Feronia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 Km. 09,9 Castelnuovo di Porto itinere sud.*|<item>A2298</item> <!-- D18 Km. 09,9 Castelnuovo di Porto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 17,8 Settebagni itinere sud HD.*|<item>A119</item> <!-- D18 km. 17,8 Settebagni itinere sud HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 22,5 Settebagni itinere nord HD.*|<item>A458</item> <!-- D18 km. 22,5 Settebagni itinere nord HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 23,0 GRA Nord.*|<item>A120</item> <!-- D18 km. 23,0 GRA Nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo Sud.*|<item>A1018</item> <!-- D19 km. 03,8 PMV S.Cesareo Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo itinere nord.*|<item>A1019</item> <!-- D19 km. 03,8 PMV S.Cesareo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 10,2 Itinere.M.Porzio.*|<item>A2007</item> <!-- D19 km. 10,2 Itinere.M.Porzio NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 15 Barriera Roma Sud.*|<item>A121</item> <!-- D19 km. 15 Barriera Roma Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 17,7 PMV Torrenova itinere Sud.*|<item>A450</item> <!-- D19 km. 17,7 PMV Torrenova itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 20,0 Gra Sud HD.*|<item>A275</item> <!-- D19 km. 20,0 Gra Sud HD NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 219,0 Gardelletta itinere nord.*|<item>A193</item> <!-- A01 km. 219,0 Gardelletta itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 221,9 Rioveggio itinere sud.*|<item>A381</item> <!-- A01 km. 221,9 Rioveggio itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 222,7 Rioveggio.*|<item>A190</item> <!-- A01 km. 222,7 Rioveggio NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 223,2 Rioveggio itinere nord.*|<item>A380</item> <!-- A01 km. 223,2 Rioveggio itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 224,4 itinere sud.*|<item>A1028</item> <!-- A01 km. 224,4 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 228,0 Serraripoli itinere sud.*|<item>A189</item> <!-- A01 km. 228,0 Serraripoli itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 236,0 Pian Del Voglio itinere sud.*|<item>A383</item> <!-- A01 km. 236,0 Pian Del Voglio itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 237,0 Crocette itinere sud.*|<item>A83</item> <!-- A01 km. 237,0 Crocette itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 238,0 Sospara itinere sud.*|<item>A188</item> <!-- A01 km. 238,0 Sospara itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 240,0 Parcheggio Madonnina Sud.*|<item>A196</item> <!-- A01 km. 240,0 Parcheggio Madonnina Sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 242,0 Roncobilaccio.*|<item>A187</item> <!-- A01 km. 242,0 Roncobilaccio NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 242,6 Roncobilaccio itinere nord.*|<item>A1259</item> <!-- A01 km. 242,6 Roncobilaccio itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 245,0 Poggettone itinere sud.*|<item>A79</item> <!-- A01 km. 245,0 Poggettone itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 246,0 Posto Neve Berti Giuliano.*|<item>A2501</item> <!-- A01 km. 246,0 Posto Neve Berti Giuliano NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 247,0 Citerna itinere nord.*|<item>A186</item> <!-- A01 km. 247,0 Citerna itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 1,15 GE-Aereop. itinere ovest.*|<item>A472</item> <!-- A10 km. 1,15 GE-Aereop. itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 3,1 Ge Aerop. itinere est.*|<item>A473</item> <!-- A10 km. 3,1 Ge Aerop. itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 4,1 GE-Sestri Pon. itinere est.*|<item>A2629</item> <!-- A10 km. 4,1 GE-Sestri Pon. itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 5,3 Pegli itinere ovest.*|<item>A1792</item> <!-- A10 km. 5,3 Pegli itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 9,4 GE-Pegli itinere est.*|<item>A484</item> <!-- A10 km. 9,4 GE-Pegli itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 14,0 A10/A26 itinere est.*|<item>A2469</item> <!-- A10 km. 14,0 A10/A26 itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 19,4 Arenzano itinere ovest.*|<item>A485</item> <!-- A10 km. 19,4 Arenzano itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 20,0 Arenzano itinere est.*|<item>A1</item> <!-- A10 km. 20,0 Arenzano itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 20,7 Arenzano itinere est.*|<item>A486</item> <!-- A10 km. 20,7 Arenzano itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 24,6 Varazze itinere ovest.*|<item>A487</item> <!-- A10 km. 24,6 Varazze itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 27,5 Varazze itinere ovest.*|<item>A4</item> <!-- A10 km. 27,5 Varazze itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 28,8 Varazze itinere est.*|<item>A488</item> <!-- A10 km. 28,8 Varazze itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 31,7 Celle Ligure itinere ovest.*|<item>A1793</item> <!-- A10 km. 31,7 Celle Ligure itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 36,0 Albisola itinere Ovest.*|<item>A489</item> <!-- A10 km. 36,0 Albisola itinere Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 37,0 Albisola itinere ovest.*|<item>A12</item> <!-- A10 km. 37,0 Albisola itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 38,7 Albisola itinere est.*|<item>A490</item> <!-- A10 km. 38,7 Albisola itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A10 km. 42,8 Savona itinere ovest.*|<item>A1053</item> <!-- A10 km. 42,8 Savona itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 4,0 Roma Nord D.T..*|<item>A117</item> <!-- D18 km. 4,0 Roma Nord D.T. NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 5,0 Feronia itinere sud.*|<item>A99</item> <!-- D18 km. 5,0 Feronia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 Km. 09,9 Castelnuovo di Porto itinere sud.*|<item>A2298</item> <!-- D18 Km. 09,9 Castelnuovo di Porto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 17,8 Settebagni itinere sud HD.*|<item>A119</item> <!-- D18 km. 17,8 Settebagni itinere sud HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 22,5 Settebagni itinere nord HD.*|<item>A458</item> <!-- D18 km. 22,5 Settebagni itinere nord HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 23,0 GRA Nord.*|<item>A120</item> <!-- D18 km. 23,0 GRA Nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo Sud.*|<item>A1018</item> <!-- D19 km. 03,8 PMV S.Cesareo Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo itinere nord.*|<item>A1019</item> <!-- D19 km. 03,8 PMV S.Cesareo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 10,2 Itinere.M.Porzio.*|<item>A2007</item> <!-- D19 km. 10,2 Itinere.M.Porzio NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 15 Barriera Roma Sud.*|<item>A121</item> <!-- D19 km. 15 Barriera Roma Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 17,7 PMV Torrenova itinere Sud.*|<item>A450</item> <!-- D19 km. 17,7 PMV Torrenova itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 20,0 Gra Sud HD.*|<item>A275</item> <!-- D19 km. 20,0 Gra Sud HD NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 286,4 Scandicci itinere sud.*|<item>A2159</item> <!-- A01 km. 286,4 Scandicci itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 288,0 Scandicci itinere nord.*|<item>A1922</item> <!-- A01 km. 288,0 Scandicci itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 293,0 Melarancio Itinere Nord.*|<item>A76</item> <!-- A01 km. 293,0 Melarancio Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 296,0 itinere sud.*|<item>A1880</item> <!-- A01 km. 296,0 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 299,7 Firenze itinere sud.*|<item>A386</item> <!-- A01 km. 299,7 Firenze itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 299,2 3a Corsia T7.*|<item>A624</item> <!-- A01 km. 299,2 3a Corsia T7 NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 302,0 Ponte a Ema itinere nord.*|<item>A71</item> <!-- A01 km. 302,0 Ponte a Ema itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 302,1 Firenze Sud itinere nord.*|<item>A387</item> <!-- A01 km. 302,1 Firenze Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 305,0 Chianti itinere nord.*|<item>A818</item> <!-- A01 km. 305,0 Chianti itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 307,0 Chianti itinere sud.*|<item>A72</item> <!-- A01 km. 307,0 Chianti itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 310,6 San Donato itinere nord.*|<item>A893</item> <!-- A01 km. 310,6 San Donato itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 313,0 Rignano itinere nord.*|<item>A80</item> <!-- A01 km. 313,0 Rignano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 318,0 Arno a Incisa itinere nord.*|<item>A73</item> <!-- A01 km. 318,0 Arno a Incisa itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 319,2 Incisa itinere sud.*|<item>A388</item> <!-- A01 km. 319,2 Incisa itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 321,0 Incisa itinere nord.*|<item>A389</item> <!-- A01 km. 321,0 Incisa itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 4,0 Roma Nord D.T..*|<item>A117</item> <!-- D18 km. 4,0 Roma Nord D.T. NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 5,0 Feronia itinere sud.*|<item>A99</item> <!-- D18 km. 5,0 Feronia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 Km. 09,9 Castelnuovo di Porto itinere sud.*|<item>A2298</item> <!-- D18 Km. 09,9 Castelnuovo di Porto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 17,8 Settebagni itinere sud HD.*|<item>A119</item> <!-- D18 km. 17,8 Settebagni itinere sud HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 22,5 Settebagni itinere nord HD.*|<item>A458</item> <!-- D18 km. 22,5 Settebagni itinere nord HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 23,0 GRA Nord.*|<item>A120</item> <!-- D18 km. 23,0 GRA Nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo Sud.*|<item>A1018</item> <!-- D19 km. 03,8 PMV S.Cesareo Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo itinere nord.*|<item>A1019</item> <!-- D19 km. 03,8 PMV S.Cesareo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 10,2 Itinere.M.Porzio.*|<item>A2007</item> <!-- D19 km. 10,2 Itinere.M.Porzio NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 15 Barriera Roma Sud.*|<item>A121</item> <!-- D19 km. 15 Barriera Roma Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 17,7 PMV Torrenova itinere Sud.*|<item>A450</item> <!-- D19 km. 17,7 PMV Torrenova itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 20,0 Gra Sud HD.*|<item>A275</item> <!-- D19 km. 20,0 Gra Sud HD NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 509,0 Flaminia itinere nord.*|<item>A104</item> <!-- A01 km. 509,0 Flaminia itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 512,9 Ponzano itinere sud.*|<item>A1834</item> <!-- A01 km. 512,9 Ponzano itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 516,0 Soratte itinere nord.*|<item>A105</item> <!-- A01 km. 516,0 Soratte itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 517,5 Ponzano itinere nord.*|<item>A1835</item> <!-- A01 km. 517,5 Ponzano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 522,0 Nazzano itinere nord.*|<item>A106</item> <!-- A01 km. 522,0 Nazzano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 523,0 Nazzano itinere sud.*|<item>A110</item> <!-- A01 km. 523,0 Nazzano itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 525 Civitella S.Paolo itinere nord.*|<item>A107</item> <!-- A01 km. 525 Civitella S.Paolo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 527,6 itinere sud.*|<item>A447</item> <!-- A01 km. 527,6 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 530 Bivio A01-Diramazione Roma Nord itinere sud.*|<item>A98</item> <!-- A01 km. 530 Bivio A01-Diramazione Roma Nord itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 533,0 itinere nord.*|<item>A457</item> <!-- A01 km. 533,0 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 542,0 Monterotondo itinere sud.*|<item>A2009</item> <!-- A01 km. 542,0 Monterotondo itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 560,0 A01-A24 itinere sud.*|<item>A270</item> <!-- A01 km. 560,0 A01-A24 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 561,0 Bivio A1-A24 itinere sud.*|<item>A271</item> <!-- A01 km. 561,0 Bivio A1-A24 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 562,0 Bivio A1-A24 itinere nord.*|<item>A272</item> <!-- A01 km. 562,0 Bivio A1-A24 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 564 itinere nord.*|<item>A273</item> <!-- A01 km. 564 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 16,0 SH25 Sud.*|<item>A2051</item> <!-- A14 km. 16,0 SH25 Sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 017,8 Itinere Nord.*|<item>A2056</item> <!-- A14 km. 017,8 Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 018,9 Itinere Sud.*|<item>A2055</item> <!-- A14 km. 018,9 Itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 20,5 SH40 Sud.*|<item>A2039</item> <!-- A14 km. 20,5 SH40 Sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 21,8 SH44 Nord.*|<item>A2044</item> <!-- A14 km. 21,8 SH44 Nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 21,8 SH44 Sud.*|<item>A2043</item> <!-- A14 km. 21,8 SH44 Sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 36,0 Castel S.Pietro itinere sud.*|<item>A339</item> <!-- A14 km. 36,0 Castel S.Pietro itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 39,4 Castel S. Pietro itinere nord.*|<item>A913</item> <!-- A14 km. 39,4 Castel S. Pietro itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 49,0 Imola itinere sud.*|<item>A340</item> <!-- A14 km. 49,0 Imola itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 50,0 Imola itinere sud.*|<item>A51</item> <!-- A14 km. 50,0 Imola itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 51,0 Imola itinere nord.*|<item>A341</item> <!-- A14 km. 51,0 Imola itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 55,7 All. Ravenna-Solarolo itinere sud.*|<item>A550</item> <!-- A14 km. 55,7 All. Ravenna-Solarolo itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km.  56,0 Solarolo-Imola iti. Sud.*|<item>A49</item> <!-- A14 km.  56,0 Solarolo-Imola iti. Sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 59,0 All. Ravenna itinere nord.*|<item>A552</item> <!-- A14 km. 59,0 All. Ravenna itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 63,0 Faenza itinere sud.*|<item>A919</item> <!-- A14 km. 63,0 Faenza itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 008,6 Barriera Ravenna itinere.*|<item>A948</item> <!-- D14 km. 008,6 Barriera Ravenna itinere NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 011,0 Lugo Cotignola Itinere Nord.*|<item>A332</item> <!-- D14 km. 011,0 Lugo Cotignola Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 024,0 Ravenna ex barriera.*|<item>A50</item> <!-- D14 km. 024,0 Ravenna ex barriera NEW -->|g' $FILE
perl -pi -e 's|.*D14 km 27,0 Ravenna Itinere Nord.*|<item>A2637</item> <!-- D14 km 27,0 Ravenna Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 000,2 PMV Bologna Casalecchio It. Sud.*|<item>A333</item> <!-- R14 km. 000,2 PMV Bologna Casalecchio It. Sud NEW -->|g' $FILE
perl -pi -e 's|.*R01 Km.1,1 Borgo Panigale dir sx.*|<item>A2483</item> <!-- R01 Km.1,1 Borgo Panigale dir sx NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 002,0 Bo Casalecchio Itinere Ovest.*|<item>A334</item> <!-- R14 km. 002,0 Bo Casalecchio Itinere Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A07 km. 87,7 Vignole Borbera itinere sud.*|<item>A482</item> <!-- A07 km. 87,7 Vignole Borbera itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A07 km. 88,0 Vignole itinere nord.*|<item>A13</item> <!-- A07 km. 88,0 Vignole itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A07 km. 89,9 Vignole Borbera itinere nord.*|<item>A483</item> <!-- A07 km. 89,9 Vignole Borbera itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A07 km. 99,2 Isola del Cantone itinere sud.*|<item>A1054</item> <!-- A07 km. 99,2 Isola del Cantone itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A07 km. 104,8 Isola del Cantone itinere nord.*|<item>A1055</item> <!-- A07 km. 104,8 Isola del Cantone itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A07 km. 107,7 nord Uscita gall Boccardo.*|<item>A4079</item> <!-- A07 km. 107,7 nord Uscita gall Boccardo NEW -->|g' $FILE
perl -pi -e 's|.*A07 km. 111,0 Stazione Busalla itinere Sud.*|<item>A2835</item> <!-- A07 km. 111,0 Stazione Busalla itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A07 km. 114,1 Busalla itinere nord.*|<item>A481</item> <!-- A07 km. 114,1 Busalla itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A07 km. 120,8 Castagna itinere nord.*|<item>A1991</item> <!-- A07 km. 120,8 Castagna itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A07 km. 125,0 GE-Bolzaneto itinere sud.*|<item>A478</item> <!-- A07 km. 125,0 GE-Bolzaneto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A07 km. 128,9 All. A7-A12 itinere sud.*|<item>A2442</item> <!-- A07 km. 128,9 All. A7-A12 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A07 km. 130,0 All.A7/A10 itinere sud.*|<item>A1791</item> <!-- A07 km. 130,0 All.A7/A10 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A07 km. 130,1 All. A07/A12 itinere nord.*|<item>A479</item> <!-- A07 km. 130,1 All. A07/A12 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 4,0 Roma Nord D.T..*|<item>A117</item> <!-- D18 km. 4,0 Roma Nord D.T. NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 5,0 Feronia itinere sud.*|<item>A99</item> <!-- D18 km. 5,0 Feronia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 Km. 09,9 Castelnuovo di Porto itinere sud.*|<item>A2298</item> <!-- D18 Km. 09,9 Castelnuovo di Porto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 17,8 Settebagni itinere sud HD.*|<item>A119</item> <!-- D18 km. 17,8 Settebagni itinere sud HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 22,5 Settebagni itinere nord HD.*|<item>A458</item> <!-- D18 km. 22,5 Settebagni itinere nord HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 23,0 GRA Nord.*|<item>A120</item> <!-- D18 km. 23,0 GRA Nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo Sud.*|<item>A1018</item> <!-- D19 km. 03,8 PMV S.Cesareo Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo itinere nord.*|<item>A1019</item> <!-- D19 km. 03,8 PMV S.Cesareo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 10,2 Itinere.M.Porzio.*|<item>A2007</item> <!-- D19 km. 10,2 Itinere.M.Porzio NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 15 Barriera Roma Sud.*|<item>A121</item> <!-- D19 km. 15 Barriera Roma Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 17,7 PMV Torrenova itinere Sud.*|<item>A450</item> <!-- D19 km. 17,7 PMV Torrenova itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 20,0 Gra Sud HD.*|<item>A275</item> <!-- D19 km. 20,0 Gra Sud HD NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 633,0 Arnara itinere sud.*|<item>A108</item> <!-- A01 km. 633,0 Arnara itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 640,8 Ceprano itinere sud.*|<item>A513</item> <!-- A01 km. 640,8 Ceprano itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 644,9 Ceprano itinere nord.*|<item>A2127</item> <!-- A01 km. 644,9 Ceprano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 656,7 Pontecorvo itinere sud.*|<item>A1905</item> <!-- A01 km. 656,7 Pontecorvo itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 661,7 Pontecorvo itinere nord.*|<item>A1891</item> <!-- A01 km. 661,7 Pontecorvo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 668,5 Cassino itinere sud.*|<item>A514</item> <!-- A01 km. 668,5 Cassino itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 671,3 Cassino itinere nord.*|<item>A515</item> <!-- A01 km. 671,3 Cassino itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 675,8 S.Vittore itinere sud.*|<item>A1308</item> <!-- A01 km. 675,8 S.Vittore itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 678,6 S.Vittore itinere nord.*|<item>A133</item> <!-- A01 km. 678,6 S.Vittore itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 680,5 S.Vittore itinere nord.*|<item>A1858</item> <!-- A01 km. 680,5 S.Vittore itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 687,0 itinere nord.*|<item>A132</item> <!-- A01 km. 687,0 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 701,1 Caianello itinere nord.*|<item>A131</item> <!-- A01 km. 701,1 Caianello itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 702,6 Caianello itinere nord.*|<item>A517</item> <!-- A01 km. 702,6 Caianello itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 728,1 S.Maria itinere sud.*|<item>A2588</item> <!-- A01 km. 728,1 S.Maria itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 732,8 Caserta Nord itinere sud.*|<item>A520</item> <!-- A01 km. 732,8 Caserta Nord itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 0,5 Arcoveggio itinere nord.*|<item>A350</item> <!-- A13 km. 0,5 Arcoveggio itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 1,5 Arcoveggio itinere sud.*|<item>A351</item> <!-- A13 km. 1,5 Arcoveggio itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 6,5 Interporto itinere nord.*|<item>A1010</item> <!-- A13 km. 6,5 Interporto itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 9,4 Interporto itinere sud.*|<item>A1011</item> <!-- A13 km. 9,4 Interporto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 11,5 Bentivoglio Ovest itinere sud.*|<item>A2756</item> <!-- A13 km. 11,5 Bentivoglio Ovest itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 20,0 Altedo itinere nord.*|<item>A352</item> <!-- A13 km. 20,0 Altedo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 21,0 Altedo itinere sud.*|<item>A353</item> <!-- A13 km. 21,0 Altedo itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 26,6 itinere sud.*|<item>A1565</item> <!-- A13 km. 26,6 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 32,5 Ferrara Sud itinere nord.*|<item>A354</item> <!-- A13 km. 32,5 Ferrara Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 33,0 Ferrara Sud itinere nord.*|<item>A62</item> <!-- A13 km. 33,0 Ferrara Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 34,6 Ferrara Sud itinere sud.*|<item>A1012</item> <!-- A13 km. 34,6 Ferrara Sud itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 40,0 Ferrara Nord itinere nord.*|<item>A355</item> <!-- A13 km. 40,0 Ferrara Nord itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 42,0 Ferrara Nord itinere sud.*|<item>A356</item> <!-- A13 km. 42,0 Ferrara Nord itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 45,6 Occhiobello itinere nord.*|<item>A1016</item> <!-- A13 km. 45,6 Occhiobello itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A13 km. 50,0 Occhiobello itinere sud.*|<item>A357</item> <!-- A13 km. 50,0 Occhiobello itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D23 km. 1,9 Itinere nord.*|<item>A1566</item> <!-- D23 km. 1,9 Itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 65,4 Faenza itinere nord.*|<item>A920</item> <!-- A14 km. 65,4 Faenza itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 73 Faenza itinere sud.*|<item>A2374</item> <!-- A14 km. 73 Faenza itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 80,0 Forli itinere sud.*|<item>A342</item> <!-- A14 km. 80,0 Forli itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 82,6 Forli itinere nord.*|<item>A343</item> <!-- A14 km. 82,6 Forli itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 89,5 Bevano Est itinere sud.*|<item>A2124</item> <!-- A14 km. 89,5 Bevano Est itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 92,6 Cesena Nord itinere sud.*|<item>A344</item> <!-- A14 km. 92,6 Cesena Nord itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 95,0 Cesena Nord itinere nord.*|<item>A345</item> <!-- A14 km. 95,0 Cesena Nord itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 98,2 Cesena itinere sud.*|<item>A909</item> <!-- A14 km. 98,2 Cesena itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 100,0 Cesena Sud A-14.*|<item>A48</item> <!-- A14 km. 100,0 Cesena Sud A-14 NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 101,0 Cesena itinere nord.*|<item>A912</item> <!-- A14 km. 101,0 Cesena itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 111,3 Area di servizio Rubicone itinere nord.*|<item>A2123</item> <!-- A14 km. 111,3 Area di servizio Rubicone itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 116,0 Rimini Nord itinere sud.*|<item>A346</item> <!-- A14 km. 116,0 Rimini Nord itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 117,0 Rimini Nord itinere sud.*|<item>A54</item> <!-- A14 km. 117,0 Rimini Nord itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 118,0 Rimini Nord itinere nord.*|<item>A347</item> <!-- A14 km. 118,0 Rimini Nord itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 126,2 Rimini Sud itinere sud.*|<item>A915</item> <!-- A14 km. 126,2 Rimini Sud itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 008,6 Barriera Ravenna itinere.*|<item>A948</item> <!-- D14 km. 008,6 Barriera Ravenna itinere NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 011,0 Lugo Cotignola Itinere Nord.*|<item>A332</item> <!-- D14 km. 011,0 Lugo Cotignola Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 024,0 Ravenna ex barriera.*|<item>A50</item> <!-- D14 km. 024,0 Ravenna ex barriera NEW -->|g' $FILE
perl -pi -e 's|.*D14 km 27,0 Ravenna Itinere Nord.*|<item>A2637</item> <!-- D14 km 27,0 Ravenna Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 000,2 PMV Bologna Casalecchio It. Sud.*|<item>A333</item> <!-- R14 km. 000,2 PMV Bologna Casalecchio It. Sud NEW -->|g' $FILE
perl -pi -e 's|.*R01 Km.1,1 Borgo Panigale dir sx.*|<item>A2483</item> <!-- R01 Km.1,1 Borgo Panigale dir sx NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 002,0 Bo Casalecchio Itinere Ovest.*|<item>A334</item> <!-- R14 km. 002,0 Bo Casalecchio Itinere Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 125,0 Bivio Fiorenza.*|<item>A268</item> <!-- A04 km. 125,0 Bivio Fiorenza NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 125,0 V.le Certosa Itinere Nord.*|<item>A582</item> <!-- A04 km. 125,0 V.le Certosa Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 126,0 Fiorenza itinere ovest.*|<item>A171</item> <!-- A04 km. 126,0 Fiorenza itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 128,5 PMV DT Dir. Ovest.*|<item>A434</item> <!-- A04 km. 128,5 PMV DT Dir. Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 129,8 Cormano itinere ovest.*|<item>A36</item> <!-- A04 km. 129,8 Cormano itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 131,0 Cormano itinere ovest.*|<item>A435</item> <!-- A04 km. 131,0 Cormano itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 133,8 Lambro itinere Ovest.*|<item>A172</item> <!-- A04 km. 133,8 Lambro itinere Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 136,5 svincolo Sesto S. Giovanni  itinere ovest.*|<item>A33</item> <!-- A04 km. 136,5 svincolo Sesto S. Giovanni  itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 137,1 Sesto S.Giovanni itinere ovest.*|<item>A436</item> <!-- A04 km. 137,1 Sesto S.Giovanni itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 138,3 Barr. Milano est.*|<item>A37</item> <!-- A04 km. 138,3 Barr. Milano est NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 140,0 Monza itinere ovest.*|<item>A437</item> <!-- A04 km. 140,0 Monza itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 142,1 Tangenziale Milano Est itinere est.*|<item>A421</item> <!-- A04 km. 142,1 Tangenziale Milano Est itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 144,4 Allacciamento Tang. Est itinere ovest.*|<item>A38</item> <!-- A04 km. 144,4 Allacciamento Tang. Est itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 147 Agrate itinere ovest.*|<item>A173</item> <!-- A04 km. 147 Agrate itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A04 km. 149,3 Cavenago itinere est.*|<item>A422</item> <!-- A04 km. 149,3 Cavenago itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 349,0 Atri itinere sud.*|<item>A311</item> <!-- A14 km. 349,0 Atri itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 358,0 Galleria Pianacce itinere sud.*|<item>A148</item> <!-- A14 km. 358,0 Galleria Pianacce itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 359,4 Itinere nord.*|<item>A1234</item> <!-- A14 km. 359,4 Itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 361,0 Cerrano itinere sud.*|<item>A149</item> <!-- A14 km. 361,0 Cerrano itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 362,5 Pescara Nord itinere sud.*|<item>A1235</item> <!-- A14 km. 362,5 Pescara Nord itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 366,5 PMV Pescara Nord itinere nord.*|<item>A312</item> <!-- A14 km. 366,5 PMV Pescara Nord itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 370,0 GALL. Cappelle itinere nord.*|<item>A1254</item> <!-- A14 km. 370,0 GALL. Cappelle itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 374,9 Pescara Ovest itinere sud.*|<item>A313</item> <!-- A14 km. 374,9 Pescara Ovest itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 378,6 Pescara Ovest itinere sud.*|<item>A1271</item> <!-- A14 km. 378,6 Pescara Ovest itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 383,7 Pescara Ovest itinere nord.*|<item>A1277</item> <!-- A14 km. 383,7 Pescara Ovest itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 388,5 Pescara Sud itinere nord.*|<item>A1266</item> <!-- A14 km. 388,5 Pescara Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 390,1 Pescara Sud itinere sud.*|<item>A1278</item> <!-- A14 km. 390,1 Pescara Sud itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 394,4 Pescara Sud itinere nord.*|<item>A553</item> <!-- A14 km. 394,4 Pescara Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 402,3 itinere sud.*|<item>A1269</item> <!-- A14 km. 402,3 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 405,4 Ortona itinere nord.*|<item>A1270</item> <!-- A14 km. 405,4 Ortona itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 008,6 Barriera Ravenna itinere.*|<item>A948</item> <!-- D14 km. 008,6 Barriera Ravenna itinere NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 011,0 Lugo Cotignola Itinere Nord.*|<item>A332</item> <!-- D14 km. 011,0 Lugo Cotignola Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 024,0 Ravenna ex barriera.*|<item>A50</item> <!-- D14 km. 024,0 Ravenna ex barriera NEW -->|g' $FILE
perl -pi -e 's|.*D14 km 27,0 Ravenna Itinere Nord.*|<item>A2637</item> <!-- D14 km 27,0 Ravenna Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 000,2 PMV Bologna Casalecchio It. Sud.*|<item>A333</item> <!-- R14 km. 000,2 PMV Bologna Casalecchio It. Sud NEW -->|g' $FILE
perl -pi -e 's|.*R01 Km.1,1 Borgo Panigale dir sx.*|<item>A2483</item> <!-- R01 Km.1,1 Borgo Panigale dir sx NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 002,0 Bo Casalecchio Itinere Ovest.*|<item>A334</item> <!-- R14 km. 002,0 Bo Casalecchio Itinere Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 00,0 Peretola itinere ovest.*|<item>A1810</item> <!-- A11 km. 00,0 Peretola itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 02,0 Osmannoro itinere ovest.*|<item>A85</item> <!-- A11 km. 02,0 Osmannoro itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 07,0 Prato Est itinere est.*|<item>A90</item> <!-- A11 km. 07,0 Prato Est itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 09,5 Prato Est itinere est.*|<item>A391</item> <!-- A11 km. 09,5 Prato Est itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 11,8 Cafaggio itinere est.*|<item>A97</item> <!-- A11 km. 11,8 Cafaggio itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 15,0 Prato Ovest itinere ovest.*|<item>A392</item> <!-- A11 km. 15,0 Prato Ovest itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 19,0 Prato Ovest itinere est.*|<item>A1049</item> <!-- A11 km. 19,0 Prato Ovest itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 26,1 Pistoia itinere ovest.*|<item>A394</item> <!-- A11 km. 26,1 Pistoia itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 28,6 Pistoia itinere est.*|<item>A395</item> <!-- A11 km. 28,6 Pistoia itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 33,0  Serravalle itinere ovest.*|<item>A2031</item> <!-- A11 km. 33,0  Serravalle itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 34 Serravalle itinere ovest.*|<item>A2025</item> <!-- A11 km. 34 Serravalle itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 35,5 Serravalle itinere ovest.*|<item>A2647</item> <!-- A11 km. 35,5 Serravalle itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 36,5 Montecatini itinere ovest.*|<item>A396</item> <!-- A11 km. 36,5 Montecatini itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 38,5 Montecatini itinere ovest.*|<item>A2097</item> <!-- A11 km. 38,5 Montecatini itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A11 km. 40 Montecatini itinere est.*|<item>A397</item> <!-- A11 km. 40 Montecatini itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 01.80 Via Marina.*|<item>A2441</item> <!-- A3 km. 01.80 Via Marina NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 03.00 S. Giovanni dir. nord.*|<item>A1574</item> <!-- A3 km. 03.00 S. Giovanni dir. nord NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 03.00 A3-A1 dir. sud.*|<item>A1593</item> <!-- A3 km. 03.00 A3-A1 dir. sud NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 15.00 Pinetina dir. nord.*|<item>A2363</item> <!-- A3 km. 15.00 Pinetina dir. nord NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 15,00 Pinetina dir. sud.*|<item>A2362</item> <!-- A3 km. 15,00 Pinetina dir. sud NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 19.70 Torre Annunziata Sud.*|<item>A2995</item> <!-- A3 km. 19.70 Torre Annunziata Sud NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 22.00 Pompei Ovest.*|<item>A1584</item> <!-- A3 km. 22.00 Pompei Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 22.60 Castellammare.*|<item>A1581</item> <!-- A3 km. 22.60 Castellammare NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 26.00 Scafati dir. nord.*|<item>A1293</item> <!-- A3 km. 26.00 Scafati dir. nord NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 26.00 Scafati dir. sud.*|<item>A1586</item> <!-- A3 km. 26.00 Scafati dir. sud NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 29.80 Angri dir. nord.*|<item>A1583</item> <!-- A3 km. 29.80 Angri dir. nord NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 36.80 Barriera Nocera.*|<item>A165</item> <!-- A3 km. 36.80 Barriera Nocera NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 36.80 Nocera Montagna.*|<item>A1533</item> <!-- A3 km. 36.80 Nocera Montagna NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 37.00 Nocera dir. sud.*|<item>A1591</item> <!-- A3 km. 37.00 Nocera dir. sud NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 40.00 Alfaterna dir. nord.*|<item>A1580</item> <!-- A3 km. 40.00 Alfaterna dir. nord NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 40.00 Alfaterna dir. sud.*|<item>A1290</item> <!-- A3 km. 40.00 Alfaterna dir. sud NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 49.00 Vietri.*|<item>A2517</item> <!-- A3 km. 49.00 Vietri NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 51.60 Gall. Castello dir. nord.*|<item>A1589</item> <!-- A3 km. 51.60 Gall. Castello dir. nord NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 51.60 Salerno.*|<item>A169</item> <!-- A3 km. 51.60 Salerno NEW -->|g' $FILE
perl -pi -e 's|.*A3 km. 51.60 Gall. Castello dir. sud.*|<item>A1546</item> <!-- A3 km. 51.60 Gall. Castello dir. sud NEW -->|g' $FILE
perl -pi -e 's|.*A25 km. 087,1 Avezzano Dx.*|<item>A2638</item> <!-- A25 km. 087,1 Avezzano Dx NEW -->|g' $FILE
perl -pi -e 's|.*A25 km. 89,1 AVEZZANO Sx.*|<item>A2636</item> <!-- A25 km. 89,1 AVEZZANO Sx NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 4,0 Roma Nord D.T..*|<item>A117</item> <!-- D18 km. 4,0 Roma Nord D.T. NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 5,0 Feronia itinere sud.*|<item>A99</item> <!-- D18 km. 5,0 Feronia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 Km. 09,9 Castelnuovo di Porto itinere sud.*|<item>A2298</item> <!-- D18 Km. 09,9 Castelnuovo di Porto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 17,8 Settebagni itinere sud HD.*|<item>A119</item> <!-- D18 km. 17,8 Settebagni itinere sud HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 22,5 Settebagni itinere nord HD.*|<item>A458</item> <!-- D18 km. 22,5 Settebagni itinere nord HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 23,0 GRA Nord.*|<item>A120</item> <!-- D18 km. 23,0 GRA Nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo Sud.*|<item>A1018</item> <!-- D19 km. 03,8 PMV S.Cesareo Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo itinere nord.*|<item>A1019</item> <!-- D19 km. 03,8 PMV S.Cesareo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 10,2 Itinere.M.Porzio.*|<item>A2007</item> <!-- D19 km. 10,2 Itinere.M.Porzio NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 15 Barriera Roma Sud.*|<item>A121</item> <!-- D19 km. 15 Barriera Roma Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 17,7 PMV Torrenova itinere Sud.*|<item>A450</item> <!-- D19 km. 17,7 PMV Torrenova itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 20,0 Gra Sud HD.*|<item>A275</item> <!-- D19 km. 20,0 Gra Sud HD NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 166,1 itinere sud.*|<item>A921</item> <!-- A01 km. 166,1 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 169,7 Modena Sud itinere sud.*|<item>A1038</item> <!-- A01 km. 169,7 Modena Sud itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 170,8 Modena Sud  itinere sud.*|<item>A601</item> <!-- A01 km. 170,8 Modena Sud  itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 172,0 Modena Sud itinere nord.*|<item>A1039</item> <!-- A01 km. 172,0 Modena Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 177,5 itinere sud.*|<item>A922</item> <!-- A01 km. 177,5 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 183,0 itinere sud.*|<item>A1137</item> <!-- A01 km. 183,0 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 189,0 A1/A14 itinere nord.*|<item>A55</item> <!-- A01 km. 189,0 A1/A14 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 194,0 All. A1/A14 Casalecchio itinere sud.*|<item>A371</item> <!-- A01 km. 194,0 All. A1/A14 Casalecchio itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 195,0 itinere nord.*|<item>A66</item> <!-- A01 km. 195,0 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 197,0 All. A1/A14 Casalecchio itinere nord.*|<item>A372</item> <!-- A01 km. 197,0 All. A1/A14 Casalecchio itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 199,0 Cantagallo itinere sud.*|<item>A67</item> <!-- A01 km. 199,0 Cantagallo itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 206,5 Sasso Marconi itinere sud.*|<item>A917</item> <!-- A01 km. 206,5 Sasso Marconi itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 207,0 Sasso Marconi Vecchia.*|<item>A918</item> <!-- A01 km. 207,0 Sasso Marconi Vecchia NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 210,8 Sasso Marconi Itinere nord.*|<item>A52</item> <!-- A01 km. 210,8 Sasso Marconi Itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 211,50 Viadotto Lama di Setta nord.*|<item>A949</item> <!-- A01 km. 211,50 Viadotto Lama di Setta nord NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 44,5 All.A26/D26 itinere nord.*|<item>A499</item> <!-- A26 km. 44,5 All.A26/D26 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 47,0 All.A26/D26 itinere sud.*|<item>A477</item> <!-- A26 km. 47,0 All.A26/D26 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 53,3 Bormida itinere sud.*|<item>A2577</item> <!-- A26 km. 53,3 Bormida itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 57,1 Alessandria Sud itinere nord.*|<item>A502</item> <!-- A26 km. 57,1 Alessandria Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 66,3 A26/A21 itinere sud.*|<item>A2586</item> <!-- A26 km. 66,3 A26/A21 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 68,0 A26/A21 itinere sud.*|<item>A501</item> <!-- A26 km. 68,0 A26/A21 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 73,8 Gall.Olimpia itinere sud.*|<item>A1775</item> <!-- A26 km. 73,8 Gall.Olimpia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 76,250 Rio Anda itinere sud.*|<item>A2553</item> <!-- A26 km. 76,250 Rio Anda itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 87,8 Casale Sud itinere nord.*|<item>A503</item> <!-- A26 km. 87,8 Casale Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 101,2  Allacc. A26-D36 itinere nord.*|<item>A1224</item> <!-- A26 km. 101,2  Allacc. A26-D36 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 105,5 itinere sud.*|<item>A2482</item> <!-- A26 km. 105,5 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 127,7 A26/A4 itinere nord.*|<item>A507</item> <!-- A26 km. 127,7 A26/A4 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 130,6 A26/A4 itinere sud.*|<item>A506</item> <!-- A26 km. 130,6 A26/A4 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 143,0 Romagnano itinere nord.*|<item>A1773</item> <!-- A26 km. 143,0 Romagnano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A26 km. 146,6 Romagnano itinere sud.*|<item>A1772</item> <!-- A26 km. 146,6 Romagnano itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D26 km. 1,4 D26/A26 itinere ovest.*|<item>A500</item> <!-- D26 km. 1,4 D26/A26 itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*D26 km. 8,0 Novi Ligure itinere est.*|<item>A2385</item> <!-- D26 km. 8,0 Novi Ligure itinere est NEW -->|g' $FILE
perl -pi -e 's|.*D36 km. 8,6 Vercelli Ovest itinere Est.*|<item>A505</item> <!-- D36 km. 8,6 Vercelli Ovest itinere Est NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 39,9 Lavagna itinere est.*|<item>A1051</item> <!-- A12 km. 39,9 Lavagna itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 42,2 Lavagna itinere ovest.*|<item>A1050</item> <!-- A12 km. 42,2 Lavagna itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 52,1 Sestri TR1/Salt itinere ovest.*|<item>A496</item> <!-- A12 km. 52,1 Sestri TR1/Salt itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 170,1 Livorno.*|<item>A209</item> <!-- A12 km. 170,1 Livorno NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 177,8 Collesalvetti.*|<item>A208</item> <!-- A12 km. 177,8 Collesalvetti NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 181,1 Malenchini Nord.*|<item>A207</item> <!-- A12 km. 181,1 Malenchini Nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 182,6 Malenchini Sud.*|<item>A206</item> <!-- A12 km. 182,6 Malenchini Sud NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 188,1 Rimazzano Nord.*|<item>A205</item> <!-- A12 km. 188,1 Rimazzano Nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 190,2 Rimazzano Sud.*|<item>A204</item> <!-- A12 km. 190,2 Rimazzano Sud NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 197,7 Santa Luce.*|<item>A203</item> <!-- A12 km. 197,7 Santa Luce NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 200,1 A.D.S. Fine.*|<item>A251</item> <!-- A12 km. 200,1 A.D.S. Fine NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 205,2 Malandrone Sud.*|<item>A200</item> <!-- A12 km. 205,2 Malandrone Sud NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 205,5 Malandrone Nord.*|<item>A199</item> <!-- A12 km. 205,5 Malandrone Nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 206,0 Rosignano entrate.*|<item>A1900</item> <!-- A12 km. 206,0 Rosignano entrate NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 206,0 Rosignano uscite.*|<item>A1901</item> <!-- A12 km. 206,0 Rosignano uscite NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 0,0 itinere nord.*|<item>A2110</item> <!-- A12 km. 0,0 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 09,9 Maccarese itinere nord.*|<item>A465</item> <!-- A12 km. 09,9 Maccarese itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 15,0 Torrimpietra Tower itinere nord.*|<item>A2008</item> <!-- A12 km. 15,0 Torrimpietra Tower itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 30,0 Cerveteri itinere sud.*|<item>A462</item> <!-- A12 km. 30,0 Cerveteri itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 39,5 Tirreno Est itinere nord.*|<item>A2081</item> <!-- A12 km. 39,5 Tirreno Est itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 50,2 Aurelia itinere nord.*|<item>A464</item> <!-- A12 km. 50,2 Aurelia itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 53,0 Aurelia itinere sud.*|<item>A463</item> <!-- A12 km. 53,0 Aurelia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A12 km. 65,0 Svincolo Tarquinia itinere sud.*|<item>A2000</item> <!-- A12 km. 65,0 Svincolo Tarquinia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 14,7 All. A16-A30 itinere est.*|<item>A524</item> <!-- A16 km. 14,7 All. A16-A30 itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 25,6 Baiano itinere est.*|<item>A526</item> <!-- A16 km. 25,6 Baiano itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 33,6 itinere ovest.*|<item>A2140</item> <!-- A16 km. 33,6 itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 36,4 Monteforte itinere ovest.*|<item>A130</item> <!-- A16 km. 36,4 Monteforte itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 40,0 Avellino itinere est.*|<item>A2498</item> <!-- A16 km. 40,0 Avellino itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 56,95 itinere est.*|<item>A2139</item> <!-- A16 km. 56,95 itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 59,8 Montemiletto itinere est.*|<item>A135</item> <!-- A16 km. 59,8 Montemiletto itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 65,0 itinere est.*|<item>A2138</item> <!-- A16 km. 65,0 itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 69,9 Benevento itinere ovest.*|<item>A527</item> <!-- A16 km. 69,9 Benevento itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 77,3 Mirabella itinere ovest.*|<item>A2149</item> <!-- A16 km. 77,3 Mirabella itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 80,6 Grottaminarda itinere est.*|<item>A1310</item> <!-- A16 km. 80,6 Grottaminarda itinere est NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 83,7 Grottaminarda itinere ovest.*|<item>A529</item> <!-- A16 km. 83,7 Grottaminarda itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 92,3 itinere ovest.*|<item>A2148</item> <!-- A16 km. 92,3 itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 98,2 itinere ovest.*|<item>A2137</item> <!-- A16 km. 98,2 itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*A16 km. 102,2 itinere est.*|<item>A129</item> <!-- A16 km. 102,2 itinere est NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 4,0 Roma Nord D.T..*|<item>A117</item> <!-- D18 km. 4,0 Roma Nord D.T. NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 5,0 Feronia itinere sud.*|<item>A99</item> <!-- D18 km. 5,0 Feronia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 Km. 09,9 Castelnuovo di Porto itinere sud.*|<item>A2298</item> <!-- D18 Km. 09,9 Castelnuovo di Porto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 17,8 Settebagni itinere sud HD.*|<item>A119</item> <!-- D18 km. 17,8 Settebagni itinere sud HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 22,5 Settebagni itinere nord HD.*|<item>A458</item> <!-- D18 km. 22,5 Settebagni itinere nord HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 23,0 GRA Nord.*|<item>A120</item> <!-- D18 km. 23,0 GRA Nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo Sud.*|<item>A1018</item> <!-- D19 km. 03,8 PMV S.Cesareo Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo itinere nord.*|<item>A1019</item> <!-- D19 km. 03,8 PMV S.Cesareo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 10,2 Itinere.M.Porzio.*|<item>A2007</item> <!-- D19 km. 10,2 Itinere.M.Porzio NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 15 Barriera Roma Sud.*|<item>A121</item> <!-- D19 km. 15 Barriera Roma Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 17,7 PMV Torrenova itinere Sud.*|<item>A450</item> <!-- D19 km. 17,7 PMV Torrenova itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 20,0 Gra Sud HD.*|<item>A275</item> <!-- D19 km. 20,0 Gra Sud HD NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 001,7 PMV S.Giuliano Itinere Sud.*|<item>A402</item> <!-- A01 km. 001,7 PMV S.Giuliano Itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 001,9 PMV S.Giuliano Itinere Nord.*|<item>A1895</item> <!-- A01 km. 001,9 PMV S.Giuliano Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 000,0 Ads S.Donato-Sv. Poasco.*|<item>A1970</item> <!-- A01 km. 000,0 Ads S.Donato-Sv. Poasco NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 003,8 All. Tang. Ovest.*|<item>A1971</item> <!-- A01 km. 003,8 All. Tang. Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 006,0 PMV Melegnano Itinere Sud.*|<item>A403</item> <!-- A01 km. 006,0 PMV Melegnano Itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 006,8 PMV Melegnano Itinere Nord.*|<item>A411</item> <!-- A01 km. 006,8 PMV Melegnano Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km 9,9 Barriera Milano Sud.*|<item>A22</item> <!-- A01 km 9,9 Barriera Milano Sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 015,3 Ads S. Zenone Sud.*|<item>A1972</item> <!-- A01 km. 015,3 Ads S. Zenone Sud NEW -->|g' $FILE
perl -pi -e 's|.*A01  km.  21,2 PMV Lodi Itinere Sud.*|<item>A404</item> <!-- A01  km.  21,2 PMV Lodi Itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A01  km.  22,3 Lodi.*|<item>A26</item> <!-- A01  km.  22,3 Lodi NEW -->|g' $FILE
perl -pi -e 's|.*A01  km.  24,0 PMV Lodi Itinere Nord.*|<item>A412</item> <!-- A01  km.  24,0 PMV Lodi Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 030,7 Itinere.*|<item>A2021</item> <!-- A01 km. 030,7 Itinere NEW -->|g' $FILE
perl -pi -e 's|.*A01  km.  36,1 PMV Casalpusterlengo Sud.*|<item>A405</item> <!-- A01  km.  36,1 PMV Casalpusterlengo Sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 038,9 PMV Casalpusterlengo Nord.*|<item>A413</item> <!-- A01 km. 038,9 PMV Casalpusterlengo Nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 043,6 Ads Somaglia.*|<item>A1986</item> <!-- A01 km. 043,6 Ads Somaglia NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 10,5 TC 11 Cassiodoro sud.*|<item>A2563</item> <!-- T04 km. 10,5 TC 11 Cassiodoro sud NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 0,7 TC1 Svincolo Arcofelice.*|<item>A2576</item> <!-- T04 km. 0,7 TC1 Svincolo Arcofelice NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 1,8 TC 2 Paramassi.*|<item>A2565</item> <!-- T04 km. 1,8 TC 2 Paramassi NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 0,0 TC0 Domitiana.*|<item>A2568</item> <!-- T04 km. 0,0 TC0 Domitiana NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 2,7 TC 3 Via Campana.*|<item>A2635</item> <!-- T04 km. 2,7 TC 3 Via Campana NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 003,6 TC 4 Solfatara.*|<item>A2569</item> <!-- T04 km. 003,6 TC 4 Solfatara NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 005,4 Astroni Est.*|<item>A2625</item> <!-- T04 km. 005,4 Astroni Est NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 005,4 TC 5 Astroni ( stazione).*|<item>A2556</item> <!-- T04 km. 005,4 TC 5 Astroni ( stazione) NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 006,6 TC 6.*|<item>A1140</item> <!-- T04 km. 006,6 TC 6 NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 7,2 TC 8 Agnano (svincolo sud).*|<item>A4528</item> <!-- T04 km. 7,2 TC 8 Agnano (svincolo sud) NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 7,7 TC 7 Agnano (stazione).*|<item>A1171</item> <!-- T04 km. 7,7 TC 7 Agnano (stazione) NEW -->|g' $FILE
perl -pi -e 's|.*T04 km 8,1 TC 9 Svincolo Italia 90.*|<item>A2575</item> <!-- T04 km 8,1 TC 9 Svincolo Italia 90 NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 9,8 TC 10 Fuorigrotta (stazione).*|<item>A4529</item> <!-- T04 km. 9,8 TC 10 Fuorigrotta (stazione) NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 011,3 TC 12.*|<item>A2562</item> <!-- T04 km. 011,3 TC 12 NEW -->|g' $FILE
perl -pi -e 's|.*T04 km. 11,8 TC 13 Vomero.*|<item>A2558</item> <!-- T04 km. 11,8 TC 13 Vomero NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 224,6 Ancona Nord itinere nord.*|<item>A301</item> <!-- A14 km. 224,6 Ancona Nord itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 227,8 Ancona Sud itinere sud.*|<item>A901</item> <!-- A14 km. 227,8 Ancona Sud itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 240,2 Loreto itinere sud.*|<item>A1268</item> <!-- A14 km. 240,2 Loreto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 248,4 Loreto itinere nord.*|<item>A303</item> <!-- A14 km. 248,4 Loreto itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 259,0 itinere nord.*|<item>A2059</item> <!-- A14 km. 259,0 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 261,0 Civitanova itinere sud.*|<item>A304</item> <!-- A14 km. 261,0 Civitanova itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 263,8 Civitanova itinere nord.*|<item>A905</item> <!-- A14 km. 263,8 Civitanova itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 275,8 Fermo itinere sud.*|<item>A906</item> <!-- A14 km. 275,8 Fermo itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 278,0 Gall. Porto S. Giorgio itinere sud.*|<item>A142</item> <!-- A14 km. 278,0 Gall. Porto S. Giorgio itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 283,3 Fermo itinere nord.*|<item>A305</item> <!-- A14 km. 283,3 Fermo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 283,5 Pedaso itinere sud.*|<item>A306</item> <!-- A14 km. 283,5 Pedaso itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 288,0 Galleria Pedaso itinere sud.*|<item>A143</item> <!-- A14 km. 288,0 Galleria Pedaso itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 293,9 Pedaso itinere nord.*|<item>A1035</item> <!-- A14 km. 293,9 Pedaso itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 294,4 Grottammare itinere sud.*|<item>A1036</item> <!-- A14 km. 294,4 Grottammare itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 296,0 Galleria S.Basso itinere nord.*|<item>A144</item> <!-- A14 km. 296,0 Galleria S.Basso itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 008,6 Barriera Ravenna itinere.*|<item>A948</item> <!-- D14 km. 008,6 Barriera Ravenna itinere NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 011,0 Lugo Cotignola Itinere Nord.*|<item>A332</item> <!-- D14 km. 011,0 Lugo Cotignola Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 024,0 Ravenna ex barriera.*|<item>A50</item> <!-- D14 km. 024,0 Ravenna ex barriera NEW -->|g' $FILE
perl -pi -e 's|.*D14 km 27,0 Ravenna Itinere Nord.*|<item>A2637</item> <!-- D14 km 27,0 Ravenna Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 000,2 PMV Bologna Casalecchio It. Sud.*|<item>A333</item> <!-- R14 km. 000,2 PMV Bologna Casalecchio It. Sud NEW -->|g' $FILE
perl -pi -e 's|.*R01 Km.1,1 Borgo Panigale dir sx.*|<item>A2483</item> <!-- R01 Km.1,1 Borgo Panigale dir sx NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 002,0 Bo Casalecchio Itinere Ovest.*|<item>A334</item> <!-- R14 km. 002,0 Bo Casalecchio Itinere Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 299,0 Gall.Grottammare itinere nord.*|<item>A151</item> <!-- A14 km. 299,0 Gall.Grottammare itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 301,0 Grottamare itinere nord.*|<item>A278</item> <!-- A14 km. 301,0 Grottamare itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 302,9 Galleria M.Secco itinere nord.*|<item>A145</item> <!-- A14 km. 302,9 Galleria M.Secco itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 305,5 Grottammare itinere nord.*|<item>A307</item> <!-- A14 km. 305,5 Grottammare itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 306,0 Galleria Croce itinere sud.*|<item>A136</item> <!-- A14 km. 306,0 Galleria Croce itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 309,9 San Benedetto T. itinere sud.*|<item>A903</item> <!-- A14 km. 309,9 San Benedetto T. itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 312,0 G. Colle di Marzio itinere sud.*|<item>A146</item> <!-- A14 km. 312,0 G. Colle di Marzio itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 313,5 San Benedetto T. itinere nord.*|<item>A308</item> <!-- A14 km. 313,5 San Benedetto T. itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 316,2 Val Vibrata itinere sud.*|<item>A309</item> <!-- A14 km. 316,2 Val Vibrata itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 327,0 Teramo itinere sud.*|<item>A904</item> <!-- A14 km. 327,0 Teramo itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 327,0 Viadot.Salinello itinere sud.*|<item>A147</item> <!-- A14 km. 327,0 Viadot.Salinello itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 329,5 Valvibrata itinere nord.*|<item>A900</item> <!-- A14 km. 329,5 Valvibrata itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 336 Teramo itinere nord.*|<item>A310</item> <!-- A14 km. 336 Teramo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 340,6 Roseto itinere sud.*|<item>A1247</item> <!-- A14 km. 340,6 Roseto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A14 km. 345,6 Roseto itinere nord.*|<item>A1288</item> <!-- A14 km. 345,6 Roseto itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 008,6 Barriera Ravenna itinere.*|<item>A948</item> <!-- D14 km. 008,6 Barriera Ravenna itinere NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 011,0 Lugo Cotignola Itinere Nord.*|<item>A332</item> <!-- D14 km. 011,0 Lugo Cotignola Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*D14 km. 024,0 Ravenna ex barriera.*|<item>A50</item> <!-- D14 km. 024,0 Ravenna ex barriera NEW -->|g' $FILE
perl -pi -e 's|.*D14 km 27,0 Ravenna Itinere Nord.*|<item>A2637</item> <!-- D14 km 27,0 Ravenna Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 000,2 PMV Bologna Casalecchio It. Sud.*|<item>A333</item> <!-- R14 km. 000,2 PMV Bologna Casalecchio It. Sud NEW -->|g' $FILE
perl -pi -e 's|.*R01 Km.1,1 Borgo Panigale dir sx.*|<item>A2483</item> <!-- R01 Km.1,1 Borgo Panigale dir sx NEW -->|g' $FILE
perl -pi -e 's|.*R14 km. 002,0 Bo Casalecchio Itinere Ovest.*|<item>A334</item> <!-- R14 km. 002,0 Bo Casalecchio Itinere Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A24 km. 075,7 Valle del Salto Sx.*|<item>A2642</item> <!-- A24 km. 075,7 Valle del Salto Sx NEW -->|g' $FILE
perl -pi -e 's|.*A24 km. 79,7 San Rocco Dx.*|<item>A2294</item> <!-- A24 km. 79,7 San Rocco Dx NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 4,0 Roma Nord D.T..*|<item>A117</item> <!-- D18 km. 4,0 Roma Nord D.T. NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 5,0 Feronia itinere sud.*|<item>A99</item> <!-- D18 km. 5,0 Feronia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 Km. 09,9 Castelnuovo di Porto itinere sud.*|<item>A2298</item> <!-- D18 Km. 09,9 Castelnuovo di Porto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 17,8 Settebagni itinere sud HD.*|<item>A119</item> <!-- D18 km. 17,8 Settebagni itinere sud HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 22,5 Settebagni itinere nord HD.*|<item>A458</item> <!-- D18 km. 22,5 Settebagni itinere nord HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 23,0 GRA Nord.*|<item>A120</item> <!-- D18 km. 23,0 GRA Nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo Sud.*|<item>A1018</item> <!-- D19 km. 03,8 PMV S.Cesareo Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo itinere nord.*|<item>A1019</item> <!-- D19 km. 03,8 PMV S.Cesareo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 10,2 Itinere.M.Porzio.*|<item>A2007</item> <!-- D19 km. 10,2 Itinere.M.Porzio NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 15 Barriera Roma Sud.*|<item>A121</item> <!-- D19 km. 15 Barriera Roma Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 17,7 PMV Torrenova itinere Sud.*|<item>A450</item> <!-- D19 km. 17,7 PMV Torrenova itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 20,0 Gra Sud HD.*|<item>A275</item> <!-- D19 km. 20,0 Gra Sud HD NEW -->|g' $FILE
perl -pi -e 's|.*A01  km.  48,1 PMV Piacenza Nord Itinere Sud.*|<item>A406</item> <!-- A01  km.  48,1 PMV Piacenza Nord Itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A01  km.  52,3 PMV Piacenza Nord Itinere Nord.*|<item>A414</item> <!-- A01  km.  52,3 PMV Piacenza Nord Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 Km. 54,5 Ponte Po.*|<item>A27</item> <!-- A01 Km. 54,5 Ponte Po NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 58,2 Piacenza Sud.*|<item>A28</item> <!-- A01 km. 58,2 Piacenza Sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 61,0  Piacenza Sud Itinere Nord.*|<item>A2778</item> <!-- A01 km. 61,0  Piacenza Sud Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 065,6 Itinere.*|<item>A2022</item> <!-- A01 km. 065,6 Itinere NEW -->|g' $FILE
perl -pi -e 's|.*A01  km.  71,4 PMV Fiorenzuola Itinere Sud.*|<item>A408</item> <!-- A01  km.  71,4 PMV Fiorenzuola Itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A01  km.  74,1 Fiorenzuola.*|<item>A32</item> <!-- A01  km.  74,1 Fiorenzuola NEW -->|g' $FILE
perl -pi -e 's|.*A01  km.  76,6 PMV Fiorenzuola Itinere Nord.*|<item>A416</item> <!-- A01  km.  76,6 PMV Fiorenzuola Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 079,8 Adp Chiaravalle.*|<item>A2006</item> <!-- A01 km. 079,8 Adp Chiaravalle NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 089,2 PMV Fidenza Iti.Sud.*|<item>A1024</item> <!-- A01 km. 089,2 PMV Fidenza Iti.Sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 92,5 Pmv Fidenza it. Nord.*|<item>A1023</item> <!-- A01 km. 92,5 Pmv Fidenza it. Nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 099,1 PMV Allac. A15 Itinere Sud.*|<item>A409</item> <!-- A01 km. 099,1 PMV Allac. A15 Itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 Km. 102,0 All. A1/A15 itinere sud.*|<item>A21</item> <!-- A01 Km. 102,0 All. A1/A15 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01  km. 103,0 PMV All. A1/A15 Itinere Nord.*|<item>A417</item> <!-- A01  km. 103,0 PMV All. A1/A15 Itinere Nord NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 4,0 Roma Nord D.T..*|<item>A117</item> <!-- D18 km. 4,0 Roma Nord D.T. NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 5,0 Feronia itinere sud.*|<item>A99</item> <!-- D18 km. 5,0 Feronia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 Km. 09,9 Castelnuovo di Porto itinere sud.*|<item>A2298</item> <!-- D18 Km. 09,9 Castelnuovo di Porto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 17,8 Settebagni itinere sud HD.*|<item>A119</item> <!-- D18 km. 17,8 Settebagni itinere sud HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 22,5 Settebagni itinere nord HD.*|<item>A458</item> <!-- D18 km. 22,5 Settebagni itinere nord HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 23,0 GRA Nord.*|<item>A120</item> <!-- D18 km. 23,0 GRA Nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo Sud.*|<item>A1018</item> <!-- D19 km. 03,8 PMV S.Cesareo Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo itinere nord.*|<item>A1019</item> <!-- D19 km. 03,8 PMV S.Cesareo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 10,2 Itinere.M.Porzio.*|<item>A2007</item> <!-- D19 km. 10,2 Itinere.M.Porzio NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 15 Barriera Roma Sud.*|<item>A121</item> <!-- D19 km. 15 Barriera Roma Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 17,7 PMV Torrenova itinere Sud.*|<item>A450</item> <!-- D19 km. 17,7 PMV Torrenova itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 20,0 Gra Sud HD.*|<item>A275</item> <!-- D19 km. 20,0 Gra Sud HD NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 334,8 Valdarno itinere sud.*|<item>A314</item> <!-- A01 km. 334,8 Valdarno itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 337,1 Valdarno itinere nord.*|<item>A315</item> <!-- A01 km. 337,1 Valdarno itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 343,0 Viadotto Caprenne itinere nord.*|<item>A74</item> <!-- A01 km. 343,0 Viadotto Caprenne itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 345,0 Viadotto Romita itinere sud.*|<item>A892</item> <!-- A01 km. 345,0 Viadotto Romita itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 355,4 Crocina itinere nord.*|<item>A1258</item> <!-- A01 km. 355,4 Crocina itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 357,8 Arezzo itinere sud.*|<item>A316</item> <!-- A01 km. 357,8 Arezzo itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 360,4 Arezzo itinere nord.*|<item>A2381</item> <!-- A01 km. 360,4 Arezzo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 361,9 itinere sud.*|<item>A2024</item> <!-- A01 km. 361,9 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 369,0 M.S.Savino itinere sud.*|<item>A318</item> <!-- A01 km. 369,0 M.S.Savino itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 373,0 M.S. Savino itinere nord.*|<item>A944</item> <!-- A01 km. 373,0 M.S. Savino itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 378,0 itinere nord.*|<item>A2030</item> <!-- A01 km. 378,0 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 383,0 Valdichiana itinere sud.*|<item>A945</item> <!-- A01 km. 383,0 Valdichiana itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 386,5 Valdichiana itinere nord.*|<item>A319</item> <!-- A01 km. 386,5 Valdichiana itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 395,0 Montepulciano itinere nord.*|<item>A2060</item> <!-- A01 km. 395,0 Montepulciano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 409,0 Chiusi itinere sud.*|<item>A946</item> <!-- A01 km. 409,0 Chiusi itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 4,0 Roma Nord D.T..*|<item>A117</item> <!-- D18 km. 4,0 Roma Nord D.T. NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 5,0 Feronia itinere sud.*|<item>A99</item> <!-- D18 km. 5,0 Feronia itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 Km. 09,9 Castelnuovo di Porto itinere sud.*|<item>A2298</item> <!-- D18 Km. 09,9 Castelnuovo di Porto itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 17,8 Settebagni itinere sud HD.*|<item>A119</item> <!-- D18 km. 17,8 Settebagni itinere sud HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 22,5 Settebagni itinere nord HD.*|<item>A458</item> <!-- D18 km. 22,5 Settebagni itinere nord HD NEW -->|g' $FILE
perl -pi -e 's|.*D18 km. 23,0 GRA Nord.*|<item>A120</item> <!-- D18 km. 23,0 GRA Nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo Sud.*|<item>A1018</item> <!-- D19 km. 03,8 PMV S.Cesareo Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 03,8 PMV S.Cesareo itinere nord.*|<item>A1019</item> <!-- D19 km. 03,8 PMV S.Cesareo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 10,2 Itinere.M.Porzio.*|<item>A2007</item> <!-- D19 km. 10,2 Itinere.M.Porzio NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 15 Barriera Roma Sud.*|<item>A121</item> <!-- D19 km. 15 Barriera Roma Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 17,7 PMV Torrenova itinere Sud.*|<item>A450</item> <!-- D19 km. 17,7 PMV Torrenova itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*D19 km. 20,0 Gra Sud HD.*|<item>A275</item> <!-- D19 km. 20,0 Gra Sud HD NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 249,0 Castagna itinere.*|<item>A184</item> <!-- A01 km. 249,0 Castagna itinere NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 250,0 Massa itinere nord.*|<item>A183</item> <!-- A01 km. 250,0 Massa itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 255,0 Aglio itinere sud.*|<item>A84</item> <!-- A01 km. 255,0 Aglio itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 257,0 Bue Morto itinere nord.*|<item>A198</item> <!-- A01 km. 257,0 Bue Morto itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 259,7 S. Andrea itinere nord.*|<item>A1029</item> <!-- A01 km. 259,7 S. Andrea itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 260,8 Barberino itinere sud.*|<item>A378</item> <!-- A01 km. 260,8 Barberino itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 262,3 Barberino itinere nord.*|<item>A379</item> <!-- A01 km. 262,3 Barberino itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 265,0 Croci itinere.*|<item>A179</item> <!-- A01 km. 265,0 Croci itinere NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 267,0 Croci itinere sud.*|<item>A180</item> <!-- A01 km. 267,0 Croci itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 269,0 Corzano itinere nord.*|<item>A2001</item> <!-- A01 km. 269,0 Corzano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 273,7 Marinella itinere sud.*|<item>A81</item> <!-- A01 km. 273,7 Marinella itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 277,0 Calenzano itinere sud.*|<item>A385</item> <!-- A01 km. 277,0 Calenzano itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 280,0 All. A1/A11.*|<item>A86</item> <!-- A01 km. 280,0 All. A1/A11 NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 280,6 Firenze Nord itinere nord.*|<item>A382</item> <!-- A01 km. 280,6 Firenze Nord itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A01 km. 285,0 Arno a Firenze itinere nord.*|<item>A88</item> <!-- A01 km. 285,0 Arno a Firenze itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A09 km. 11,7 Origgio itinere sud.*|<item>A588</item> <!-- A09 km. 11,7 Origgio itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A09 km. 12,050 Saronno itinere nord.*|<item>A599</item> <!-- A09 km. 12,050 Saronno itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A09 km. 15,7 Saronno itinere nord.*|<item>A1250</item> <!-- A09 km. 15,7 Saronno itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A09 km. 17,6 Saronno itinere sud.*|<item>A594</item> <!-- A09 km. 17,6 Saronno itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A09 km. 22,7 Lomazzo itinere nord.*|<item>A595</item> <!-- A09 km. 22,7 Lomazzo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A09 km. 26,8 Lomazzo Itinere Sud.*|<item>A592</item> <!-- A09 km. 26,8 Lomazzo Itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A09 km. 30,0 Fino Mornasco itinere nord.*|<item>A20</item> <!-- A09 km. 30,0 Fino Mornasco itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A09 km. 33,0 Como Grandate itinere nord.*|<item>A25</item> <!-- A09 km. 33,0 Como Grandate itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A09 km. 34,0 Como Sud itinere nord.*|<item>A23</item> <!-- A09 km. 34,0 Como Sud itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A09 km. 36,0 Como S. Fermo itinere nord.*|<item>A593</item> <!-- A09 km. 36,0 Como S. Fermo itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A09 km. 39,6 Como M. Olimpino itinere sud.*|<item>A1257</item> <!-- A09 km. 39,6 Como M. Olimpino itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A09 km. 40,0 Galleria Quarcino itinere sud.*|<item>A24</item> <!-- A09 km. 40,0 Galleria Quarcino itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A09 km. 42,0 Dogana itinere sud.*|<item>A34</item> <!-- A09 km. 42,0 Dogana itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 130,6 SV. MORGEX 600.*|<item>A1380</item> <!-- A5 km. 130,6 SV. MORGEX 600 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 131,4 MORGEX N 701.*|<item>A1814</item> <!-- A5 km. 131,4 MORGEX N 701 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 134,0 MORGEX N 715.*|<item>A1822</item> <!-- A5 km. 134,0 MORGEX N 715 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 134,0 MORGEX S 751.*|<item>A1368</item> <!-- A5 km. 134,0 MORGEX S 751 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 134,3 P.S.DIDIER N 801.*|<item>A1313</item> <!-- A5 km. 134,3 P.S.DIDIER N 801 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 137,6 P.S.DIDIER S 854.*|<item>A1340</item> <!-- A5 km. 137,6 P.S.DIDIER S 854 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 137,6 PALLESIEUX 850.*|<item>A1819</item> <!-- A5 km. 137,6 PALLESIEUX 850 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 137,7 PALLESIEUX 849.*|<item>A1335</item> <!-- A5 km. 137,7 PALLESIEUX 849 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 137,9 P.S.DIDIER N 821.*|<item>A1518</item> <!-- A5 km. 137,9 P.S.DIDIER N 821 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 137,9 P.S.DIDIER S 851.*|<item>A1337</item> <!-- A5 km. 137,9 P.S.DIDIER S 851 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 138,6 P.S.DIDIER N 822.*|<item>A1334</item> <!-- A5 km. 138,6 P.S.DIDIER N 822 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 138,8 COURMAYEUR 800.*|<item>A1312</item> <!-- A5 km. 138,8 COURMAYEUR 800 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 138,8 DOLONNE N 901.*|<item>A1514</item> <!-- A5 km. 138,8 DOLONNE N 901 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 139,1 DOLONNE N 902.*|<item>A1515</item> <!-- A5 km. 139,1 DOLONNE N 902 NEW -->|g' $FILE
perl -pi -e 's|.*A5 km. 142,7 DOLONNE S 920.*|<item>A1508</item> <!-- A5 km. 142,7 DOLONNE S 920 NEW -->|g' $FILE
perl -pi -e 's|.*D08 km. 1,1 D08/A08 itinere sud.*|<item>A2768</item> <!-- D08 km. 1,1 D08/A08 itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D08 km. 2,5 Gallarate Ovest entrata.*|<item>A791</item> <!-- D08 km. 2,5 Gallarate Ovest entrata NEW -->|g' $FILE
perl -pi -e 's|.*D08 km. 8,1 Besnate itinere sud.*|<item>A598</item> <!-- D08 km. 8,1 Besnate itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*D08 km. 8,1 Vergiate itinere nord.*|<item>A597</item> <!-- D08 km. 8,1 Vergiate itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*D08 km. 12,1 Diram. Gallarate Gattico Vergiate (Va).*|<item>A1255</item> <!-- D08 km. 12,1 Diram. Gallarate Gattico Vergiate (Va) NEW -->|g' $FILE
perl -pi -e 's|.*D08 km. 16,9 Castelletto T. itinere ovest.*|<item>A509</item> <!-- D08 km. 16,9 Castelletto T. itinere ovest NEW -->|g' $FILE
perl -pi -e 's|.*D08 km. 20,3 D 08 Km.20,3 itinere Ovest.*|<item>A2554</item> <!-- D08 km. 20,3 D 08 Km.20,3 itinere Ovest NEW -->|g' $FILE
perl -pi -e 's|.*A08 km. 1,2 Bollate itinere nord.*|<item>A1025</item> <!-- A08 km. 1,2 Bollate itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A08 Km. 2,2 Milano Fiera itinere nord.*|<item>A1226</item> <!-- A08 Km. 2,2 Milano Fiera itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A08 km. 3,9 Bollate itinere sud.*|<item>A590</item> <!-- A08 km. 3,9 Bollate itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A08 km. 5,6 Milano Nord itinere nord.*|<item>A42</item> <!-- A08 km. 5,6 Milano Nord itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A08 km. 6,5 PMV Villoresi itinere Sud.*|<item>A589</item> <!-- A08 km. 6,5 PMV Villoresi itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A08 km. 7,6 Villoresi itinere sud.*|<item>A35</item> <!-- A08 km. 7,6 Villoresi itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A08 km. 8,9 A08/A09 itinere nord.*|<item>A583</item> <!-- A08 km. 8,9 A08/A09 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A08 km. 11,0 A09/A08 itinere nord.*|<item>A1248</item> <!-- A08 km. 11,0 A09/A08 itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A08 Km. 12,2 Origgio Itinere Sud.*|<item>A596</item> <!-- A08 Km. 12,2 Origgio Itinere Sud NEW -->|g' $FILE
perl -pi -e 's|.*A08 km. 13,5 Legnano itinere nord.*|<item>A584</item> <!-- A08 km. 13,5 Legnano itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A08 km. 17,0 Castellanza itinere nord.*|<item>A2656</item> <!-- A08 km. 17,0 Castellanza itinere nord NEW -->|g' $FILE
perl -pi -e 's|.*A08 km. 20,4 Castellanza itinere sud.*|<item>A2657</item> <!-- A08 km. 20,4 Castellanza itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A08 km. 24,5 Svincolo Busto Arsizio.*|<item>A1238</item> <!-- A08 km. 24,5 Svincolo Busto Arsizio NEW -->|g' $FILE
perl -pi -e 's|.*A08 km. 25,6 Busto Arsizio itinere sud.*|<item>A587</item> <!-- A08 km. 25,6 Busto Arsizio itinere sud NEW -->|g' $FILE
perl -pi -e 's|.*A08 km. 27,2 itinere nord.*|<item>A1786</item> <!-- A08 km. 27,2 itinere nord NEW -->|g' $FILE

done