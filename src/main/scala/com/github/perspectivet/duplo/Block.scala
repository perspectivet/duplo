package com.github.perspectivet.duplo

import org.openrdf.Sesame
import org.openrdf.repository.sail._
import org.openrdf.repository.RepositoryResult
import org.openrdf.repository.http.HTTPRepository
import org.openrdf.repository.manager.RemoteRepositoryManager
import org.openrdf.model.impl._
import org.w3.banana.sesame.SesameStore
import scala.concurrent._
import scalax.io._
import java.net.URL
import org.openrdf.query.QueryLanguage

//import com.bigdata.rdf.sail

object BlockUtil {
//  val sesameServerURL = new URL()
  //val manager = new RemoteRepositoryManager("http://localhost:8080/openrdf-sesame/")

  def test() = {
    //val repo = new HttpRespository("http://localhost:8080/openrdf-sesame/","bigdata")

    try {
    val repo = new HTTPRepository("http://localhost:8080/openrdf-sesame/","bigdata")
   repo.initialize
      //val subject = "<http://bbp.epfl.ch/ontology/provenance/File_1100>"
    val subject = "?s"
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
    while (result.hasNext) {
      val res = result.next()
      println("result:" + res.toString)
      graph.add(res)
    }

    result.close
    conn.close
    //  service.login("USERNAME", "PASSWORD")
    //val store = new SesameStore(repo)
    repo.shutDown()
    } catch {
      case e:Exception => e.printStackTrace()
    }
  }
}
/*
  val foaf = FOAFPrefix(ops)

  val resource = Resource.fromFile("rdf-test-suite/src/main/resources/new-tr.rdf")

  val graph = reader.read(resource, "http://example.com") getOrElse sys.error("ouch")

  val graph1: Rdf#Graph = (
    bnode("betehess")
    -- foaf.name ->- "Alexandre".lang("fr")
    -- foaf.title ->- "Mr"
  ).graph

  val graph2: Rdf#Graph = (
    bnode("betehess")
    -- foaf.name ->- "Alexandre".lang("fr")
    -- foaf.knows ->- (
      URI("http://bblfish.net/#hjs")
      -- foaf.name ->- "Henry Story"
      -- foaf.currentProject ->- URI("http://webid.info/")
    )
  ).graph

  override def beforeAll(): Unit = {

    val init = store.execute {
      for {
        _ <- Command.append(URI("http://example.com/graph1"), graph1.toIterable)
        _ <- Command.append(URI("http://example.com/graph2"), graph2.toIterable)
        _ <- Command.append(URI("http://example.com/graph"), graph.toIterable)
      } yield ()
    }
    init.getOrFail()

  }

  "new-tr.rdf must have Alexandre Bertails as an editor" in {

    val query = SelectQuery("""
                           |prefix : <http://www.w3.org/2001/02pd/rec54#>
                           |prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                           |prefix contact: <http://www.w3.org/2000/10/swap/pim/contact#>
                           |
                           |SELECT DISTINCT ?name WHERE {
                           |  graph <http://example.com/graph> {
                           |    ?thing :editor ?ed .
                           |    ?ed contact:fullName ?name
                           |  }
                           |}""".stripMargin)

    val names: Future[Iterable[String]] = sparqlEngine.executeSelect(query).map(_.toIterable.map {
      row => row("name").flatMap(_.as[String]) getOrElse sys.error("")
    })

    names.getOrFail() must contain("Alexandre Bertails")

  }

  "new-tr.rdf must have Alexandre Bertails as an editor (with-bindings version)" in {

    val query = SelectQuery("""
                           |prefix : <http://www.w3.org/2001/02pd/rec54#>
                           |prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                           |prefix contact: <http://www.w3.org/2000/10/swap/pim/contact#>
                           |
                           |SELECT DISTINCT ?name WHERE {
                           |  graph ?g {
                           |    ?thing :editor ?ed .
                           |    ?ed ?prop ?name
                           |  }
                           |}""".stripMargin)

    val bindings = Map("g" -> URI("http://example.com/graph"),
      "thing" -> URI("http://www.w3.org/TR/2012/CR-rdb-direct-mapping-20120223/"),
      "prop" -> URI("http://www.w3.org/2000/10/swap/pim/contact#fullName"))

    val names: Future[Iterable[String]] = sparqlEngine.executeSelect(query, bindings).map(_.toIterable.map {
      row => row("name").flatMap(_.as[String]) getOrElse sys.error("")
    })

    names.getOrFail() must have size (4)

    names.getOrFail() must contain("Alexandre Bertails")

  }

  "the identity Sparql Construct must work as expected" in {

    val query = ConstructQuery("""
                              |CONSTRUCT {
                              |  ?s ?p ?o
                              |} WHERE {
                              |  graph <http://example.com/graph> {
                              |    ?s ?p ?o
                              |  }
                              |}""".stripMargin)

    val clonedGraph = sparqlEngine.executeConstruct(query).getOrFail()

    assert(clonedGraph isIsomorphicWith graph)
  }

  "Alexandre Bertails must appear as an editor in new-tr.rdf" in {

    val query = AskQuery("""
                        |prefix : <http://www.w3.org/2001/02pd/rec54#>
                        |prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                        |prefix contact: <http://www.w3.org/2000/10/swap/pim/contact#>
                        |prefix xsd: <http://www.w3.org/2001/XMLSchema#>
                        |
                        |ASK {
                        |  graph <http://example.com/graph> {
                        |    ?thing :editor ?ed .
                        |    ?ed contact:fullName "Alexandre Bertails"^^xsd:string
                        |  }
                        |}""".stripMargin)

    val alexIsThere = sparqlEngine.executeAsk(query).getOrFail()

    alexIsThere must be(true)

  }

  "Alexandre Bertails must appear as an editor in new-tr.rdf (with-bindings version)" in {

    val query = AskQuery("""
                        |prefix : <http://www.w3.org/2001/02pd/rec54#>
                        |prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                        |prefix contact: <http://www.w3.org/2000/10/swap/pim/contact#>
                        |prefix xsd: <http://www.w3.org/2001/XMLSchema#>
                        |
                        |ASK {
                        |  graph ?g {
                        |    ?thing :editor ?ed .
                        |    ?ed ?prop ?name
                        |  }
                        |}""".stripMargin)
    val bindings = Map(
      "g" -> URI("http://example.com/graph"),
      "thing" -> URI("http://www.w3.org/TR/2012/CR-rdb-direct-mapping-20120223/"),
      "prop" -> URI("http://www.w3.org/2000/10/swap/pim/contact#fullName"),
      "name" -> "Alexandre Bertails".toNode)

    val alexIsThere = sparqlEngine.executeAsk(query, bindings).getOrFail()

    alexIsThere must be(true)

  }

  "betehess must know henry" in {

    val query = AskQuery("""
                        |prefix foaf: <http://xmlns.com/foaf/0.1/>
                        |ASK {
                        |  GRAPH <http://example.com/graph2> {
                        |    [] foaf:knows <http://bblfish.net/#hjs>
                        |  }
                        |}""".stripMargin)

    val alexKnowsHenry = sparqlEngine.executeAsk(query).getOrFail()

    alexKnowsHenry must be(true)

  }

}
*/
