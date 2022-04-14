package bean;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

import java.io.InputStream;

public class Individuals {
    public static void main(String[] args) {
        OntModel model1= ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM);
        String inputFileName1="/home/arjun/IdeaProjects/universityOntology/src/main/resources/University.owl";

        InputStream in = FileManager.get().open( inputFileName1 );
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: " + inputFileName1 + " not found");
        }
        model1.read(in, null);

        System.out.println("\n---------Individuals in BaseModel-----------------\n ");
        ExtendedIterator classes = model1.listClasses();

        while (classes.hasNext())
        {
            OntClass thisClass = (OntClass) classes.next();
//		      System.out.println("Found class: " + thisClass.toString());

            ExtendedIterator instances = thisClass.listInstances();

            while (instances.hasNext())
            {
                Individual thisInstance = (Individual) instances.next();

                String s=thisInstance.toString();
                if(s.contains("#"))
                {
                    String[] a = s.split("#");
                    // System.out.println(a[1]);
                    System.out.println("           Properties of Individual :"+a[1]);
                }
                StmtIterator it = thisInstance.listProperties();
                while(it.hasNext())
                {
                    Statement r = it.next();
                    if (r.getObject().isLiteral()) {

                        //System.out.println(r.getLiteral().getLexicalForm().toString() + "\n");


                        System.out.println("                                "+r.getLiteral().getLexicalForm().toString()+" type = "+r.getPredicate().getLocalName());

                    }
//
                }

            }

        }




        System.out.println("\n");
    }
}
