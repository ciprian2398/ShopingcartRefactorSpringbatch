package springbatchexporter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlBasedContextExporter {

	public static void main(String areg[]){

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-batch-context.xml");

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("productResultJob");

		JobStarter.executeJob(jobLauncher, job);
	}
}
