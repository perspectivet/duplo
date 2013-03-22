package com.github.perspectivet.duplo

import org.specs2.mutable._

//import org.openrdf.model.impl.{StatementImpl,URIImpl}
import org.openrdf.rio.RDFFormat
import org.w3.banana.{RDF => BRDF}
import org.w3.banana.{Prefix => BPrefix}
import org.w3.banana._
import org.w3.banana.sesame._
import org.w3.banana.sesame.Sesame._
import org.w3.banana.sesame.SesameOperations._
import org.openrdf.sail.memory.MemoryStore
import org.openrdf.repository.Repository
import org.openrdf.repository.sail.SailRepository
import scala.util._
import scala.concurrent._
import scala.concurrent.util._

class BlockSpec extends Specification {

  //import org.w3.banana.diesel._
  //import org.w3.banana.diesel.ops._

  val base = BPrefix[Sesame]("rdfs", "http://www.w3.org/2000/01/rdf-schema#")
  //val rdf = RDFPrefix[Rdf]
  val contact = BPrefix[Sesame]("contact", "http://www.w3.org/2000/10/swap/pim/contact#")

  "retrieve a subjects Block" in {
    val subject = "?s"

    /*val bar: Sesame#Graph = (
      URI("http://example.com/foo")
      -- rdf("foo") ->- "foo"
      -- rdf("bar") ->- "bar"
    ).graph*/

    val b = new Block[Sesame](subject)
    //val subs = b.graph.get / base.subClassOf
    //val 
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
