package ma.ensat.XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Notes_Converter {
    public static void converter() {
        try {
            File myObj = new File("src/Excel_Csv/Notes.csv");
            File myWriter = new File("src/XML/Notes.xml");
            myWriter.createNewFile();
            FileWriter output = new FileWriter(myWriter);
            output.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?> \n");
            output.write("<Notes>\n");
            Scanner scanner = new Scanner(myObj);

            String first = scanner.nextLine();
            String[] Modules = first.split(",");

            String second = scanner.nextLine();
            String[] elements = second.split(",");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] Valeurs = line.split(",");

                if (Valeurs.length >= 3) {
                    output.write("\t<Etudiant>\n");
                    output.write("\t\t<CIN>" + Valeurs[0] + "</CIN>\n");
                    output.write("\t\t<NOM>" + Valeurs[1] + "</NOM>\n");
                    output.write("\t\t<PRENOM>" + Valeurs[2] + "</PRENOM>\n");

                    int moduleIndex = 3;
                    int elementIndex = 3;

                    while (moduleIndex < Modules.length && elementIndex < elements.length) {
                        // Ajouter un module
                        if (moduleIndex < Modules.length) {
                            output.write("\t\t<Module Code=\"" + Modules[moduleIndex] + "\" Designation=\"" + Modules[moduleIndex + 1] + "\" >\n");
                        }

                        // Ajouter les matières
                        output.write("\t\t\t<Matières>\n");
                        for (int j = elementIndex; j < elementIndex + 3 && j < elements.length; j++) {
                            if (!elements[j].isEmpty()) {
                                if (j < Valeurs.length) {
                                    output.write("\t\t\t\t<Matière Designation=\"" + elements[j] + "\" >" + Valeurs[j] + "</Matière>\n");
                                }
                            }
                        }
                        output.write("\t\t\t</Matières>\n");

                        // Ajouter la moyenne, si elle existe
                        if (elementIndex + 3 < Valeurs.length) {
                            output.write("\t\t\t<Moyenne>" + Valeurs[elementIndex + 3] + "</Moyenne>\n");
                        }
                        output.write("\t\t</Module>\n");

                        // Passer au module suivant et aux éléments suivants
                        moduleIndex += 2; // Chaque module prend 2 colonnes (Code, Designation)
                        elementIndex += 3; // Chaque élément prend 3 colonnes (Matières, Moyenne, etc.)
                    }

                    // Ajouter la moyenne générale (si elle est présente)
                    if (Valeurs.length > 51) {
                        output.write("\t\t<MoyenneGénérale>" + Valeurs[51] + "</MoyenneGénérale>\n");
                    }

                    output.write("\t</Etudiant>\n");
                }
            }

            output.write("</Notes>\n");
            output.close();
            System.out.println("Conversion réussie");
        } catch (IOException e) {
            System.out.println("Une erreur est survenue");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        converter();
    }
}
