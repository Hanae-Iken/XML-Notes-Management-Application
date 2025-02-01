declare variable $etudiants := doc('C:/Users/Lenovo PC/Documents/Ginf2/s1/XML/ProjetXML-El-Otmani-Iken/Projet_XML/XML-Notes-Management-Application/src/XML/Etudiants.xml')/Etudiants/Etudiant;

(:let $etudiants := doc("etudiants.xml")/Etudiants/Etudiant:)

(: Diviser les étudiants en groupes de 24 :)
let $groupes := for $i in 1 to ceiling(count($etudiants) div 24)
                return <Groupe numero="{$i}">
                         {
                           for $etudiant in subsequence($etudiants, ($i - 1) * 24 + 1, 24)
                           return $etudiant
                         }
                       </Groupe>

(: Retourner les groupes :)
return <Groupes>{$groupes}</Groupes>