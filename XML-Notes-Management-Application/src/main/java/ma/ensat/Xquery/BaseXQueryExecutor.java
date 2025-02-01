package ma.ensat.Xquery;

import org.basex.core.Context;
import org.basex.query.*;
import org.basex.query.value.node.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class BaseXQueryExecutor {
    public static void main(String[] args) {
        try {
            // Créer un contexte BaseX
            Context context = new Context();

            // Charger le fichier XQuery
            String queryFilePath = "src/Xquery/ReussitXquery.xquery"; // Chemin vers votre fichier XQuery
            String query = new String(Files.readAllBytes(Paths.get(queryFilePath)));

            // Exécuter la requête XQuery
            QueryProcessor processor = new QueryProcessor(query, context);
            ANode result = (ANode) processor.value(); // Utilisation de value()

            // Convertir le résultat en chaîne de caractères
            String resultString = result.toString();

            // Chemin du fichier de sortie
            String outputFilePath = "src/XML/resultReussit.xml"; // Chemin où le fichier XML sera créé

            // Écrire le résultat dans un fichier XML
            Files.write(Paths.get(outputFilePath), resultString.getBytes(), StandardOpenOption.CREATE);

            // Afficher un message de confirmation
            System.out.println("Le résultat a été écrit dans le fichier : " + outputFilePath);

            // Fermer le contexte
            context.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
