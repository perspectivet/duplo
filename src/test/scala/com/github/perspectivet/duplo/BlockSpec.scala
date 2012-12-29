package com.github.perspectivet.duplo

import org.specs2.mutable._

//import org.openrdf.model.impl.{StatementImpl,URIImpl}
import org.openrdf.rio.RDFFormat
import org.w3.banana.{RDF => BRDF}
import org.w3.banana.{Prefix => BPrefix}
import org.w3.banana.sesame.{Sesame => BSesame}
import org.w3.banana._

class BlockSpec extends Specification {
  //val foo = BlockUtil.store
  //import org.w3.banana.diesel._
  //import org.w3.banana.diesel.ops._

  val base = BPrefix[BSesame]("rdfs", "http://www.w3.org/2000/01/rdf-schema#")
  //val rdf = RDFPrefix[Rdf]
  val contact = BPrefix[BSesame]("contact", "http://www.w3.org/2000/10/swap/pim/contact#")

  "insert an rdf file" in {
    /*val rdfFile = "src/test/resources/locations.rdf"
     val results = rest.putFile(rdfFile,RDFFormat.RDFXML)
     println("results:" + results)
     success*/
    val subject = "?s"
    val b = new Block[BSesame](subject)
    val subs = b.graph / base.subClassOf
    success
  }

/*
 * use for some future test case
 * 
      val rdf = """
      <rdf:RDF xmlns=\"http://purl.uniprot.org/core/\" xmlns:dcterms=\"http://purl.org/dc/terms/\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:skos=\"http://www.w3.org/2004/02/skos/core#\" xmlns:bibo=\"http://purl.org/ontology/bibo/\" xmlns:foaf=\"http://xmlns.com/foaf/0.1/\">
      <owl:Ontology rdf:about="">
      <owl:imports rdf:resource=\"http://purl.uniprot.org/core/\"/>
      </owl:Ontology>
      <rdf:Description rdf:about=\"http://purl.uniprot.org/go/0000023\">
    <rdf:type rdf:resource=\"http://purl.uniprot.org/core/Concept\"/>
    <rdfs:label>maltose metabolic process</rdfs:label>
    <rdfs:label>malt sugar metabolic process</rdfs:label>
    <rdfs:label>malt sugar metabolism</rdfs:label>
    <rdfs:label>maltose metabolism</rdfs:label>
    <rdfs:comment>The chemical reactions and pathways involving the disaccharide maltose (4-O-alpha-D-glucopyranosyl-D-glucopyranose), an intermediate in the catabolism of glycogen and starch.</rdfs:comment>
    <rdfs:subClassOf rdf:resource=\"http://purl.uniprot.org/go/0005984\"/>
    <skos:narrower rdf:resource=\"http://purl.uniprot.org/go/0000025\"/>
    <skos:narrower rdf:resource=\"http://purl.uniprot.org/go/0000024\"/>
    <skos:exactMatch rdf:resource=\"http://www.geneontology.org/go#GO:0000023\"/>
      </rdf:Description>
      </rdf:RDF>
      """
*/

}
