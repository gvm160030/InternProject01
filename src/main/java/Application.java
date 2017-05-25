import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;


@EnableSolrRepositories(basePackages="com.solr.project.Repo", multicoreSupport=true)
public class Application {

	@Bean
    public HttpSolrServer solrServer(@Value("${solr.server.url}") String solrHost) {
	return new HttpSolrServer(solrHost);
    }

    @Bean
    public SolrTemplate solrTemplate(SolrServer solrServer) {
	return new SolrTemplate(solrServer);
    }
}
