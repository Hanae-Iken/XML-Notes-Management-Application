declare variable $file1 := doc('C:/Users/Lenovo PC/Documents/Ginf2/s1/XML/ProjetXML-El-Otmani-Iken/Projet_XML/XML-Notes-Management-Application/src/XML/Notes.xml');
declare variable $file2 := doc('C:/Users/Lenovo PC/Documents/Ginf2/s1/XML/ProjetXML-El-Otmani-Iken/Projet_XML/XML-Notes-Management-Application/src/XML/Etudiants.xml');

<Result>
{
    for $etudiant1 in $file1//Etudiant
    let $etudiant2 := $file2//Etudiant[CodeApogee = $etudiant1/CodeApogee]
    where xs:decimal($etudiant1/Module[1]/Moyenne) > 12
    return
        <Etudiant>
            <CodeApogee>{$etudiant1/CodeApogee/text()}</CodeApogee>
            <Nom>{$etudiant2/Nom/text()}</Nom>
            <Prenom>{$etudiant2/Prenom/text()}</Prenom>
            <DateNaissance>{$etudiant2/DateNaissance/text()}</DateNaissance>
            <LieuNaissance>{$etudiant2/LieuNaissance/text()}</LieuNaissance>
            <Moyenne>{$etudiant1/Module[1]/Moyenne/text()}</Moyenne>
        </Etudiant>
}
</Result>