package br.com.devires;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gcp.autoconfigure.datastore.GcpDatastoreProperties;
import org.springframework.cloud.gcp.core.UserAgentHeaderProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.gax.core.CredentialsProvider;
import com.google.cloud.NoCredentials;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;

@Configuration
@ConditionalOnProperty(name = "spring.cloud.gcp.emulator-enabled", havingValue = "true")
@EnableConfigurationProperties(GcpDatastoreProperties.class)
public class GcpDatastoreEmulatorConfig {

	@Value("${spring.cloud.gcp.datastore.emulator-host}")
	private String emulatorHost;

	@Bean
	public CredentialsProvider credentialsProvider() {
		return NoCredentials::getInstance;
	}

	@Bean
	public Datastore datastore(GcpDatastoreProperties gcpDatastoreProperties) throws IOException {
		DatastoreOptions.Builder builder = DatastoreOptions.newBuilder()
				.setProjectId(gcpDatastoreProperties.getProjectId()).setHost(emulatorHost)
				.setHeaderProvider(new UserAgentHeaderProvider(this.getClass()))
				.setCredentials(credentialsProvider().getCredentials());
		return builder.build().getService();
	}

}
