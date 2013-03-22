package com.github.perspectivet.duplo

import java.net.URL

import org.w3.banana._
import org.w3.banana.sesame._
import org.w3.banana.sesame.SesamePrefix._
//import org.w3.banana.{ RDF => BRDF }

import org.w3.banana.sesame.SesameStore
import org.w3.banana.sesame.SesameOperations

import org.openrdf.query.QueryLanguage
import org.openrdf.model._
import org.openrdf.model.impl.{ GraphImpl, StatementImpl, LiteralImpl }
import org.openrdf.repository.sail._
import org.openrdf.repository.RepositoryResult
import org.openrdf.repository.http.HTTPRepository
import org.openrdf.repository.manager.RemoteRepositoryManager
import org.openrdf.sail.SailException
import org.openrdf.query._
import org.openrdf.query.impl._
import org.openrdf.sail.memory.MemoryStore

import scala.collection.JavaConversions._
import scala.concurrent._
import scala.util.Try
import scalax.io._
import scalaz.Free

import java.util.concurrent.{ ExecutorService, Executors }

trait BlockBase[Rdf <: RDF] {

  val sesameServerURL = new URL("http://localhost:8080/openrdf-sesame/")

  val sesameStore: SesameStore = {
    SesameStore {
      val repo = new SailRepository(new MemoryStore)
      repo.initialize()
      repo
    }
  }

  val graphStore = GraphStore[Sesame, Future](sesameStore)

  def getGraph(subject: String): Try[Sesame#Graph] = {

    try {
      val repo = new HTTPRepository(sesameServerURL.toString, "bigdata")
      repo.initialize
      val construct = """
      CONSTRUCT { %s ?p ?o }
      WHERE { %s ?p ?o }
      """
      val query = construct format (subject, subject)
      println("query:" + query)
      val conn = repo.getConnection()
      val gq = conn.prepareGraphQuery(QueryLanguage.SPARQL, query)
      val result = gq.evaluate()

      val graph = new GraphImpl
      var cnt = 0
      while (result.hasNext) {
        val res = result.next()
        graph.add(res)
        cnt += 1
      }
      println("count:" + cnt)

      result.close
      conn.close
      repo.shutDown()

      Try { graph }
    } catch {
      case e: Exception => e.printStackTrace(); throw e
    }
  }
}

/*
class BlockPrefix[Rdf <: RDF](ops: RDFOps[Rdf]) extends PrefixBuilder("rdfs", "http://www.w3.org/2000/01/rdf-schema#")(ops) {
  val subClassOf = apply("subClassOf")
}
*/

class Block[Rdf <: RDF](val subject: String) extends BlockBase[Rdf] {

  val graph: Try[Sesame#Graph] = getGraph(subject)
}
/*
  val foaf = FOAFPrefix(ops)

  val resource = Resource.fromFile("rdf-test-suite/src/main/resources/new-tr.rdf")

  val graph = reader.read(resource, "http://example.com") getOrElse sys.error("ouch")

*/
