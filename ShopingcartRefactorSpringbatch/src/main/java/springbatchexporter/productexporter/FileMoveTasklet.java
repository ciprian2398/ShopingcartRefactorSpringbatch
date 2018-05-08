package springbatchexporter.productexporter;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMoveTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        Path fileToMovePath = Paths.get("src/main/resources/csv/data.csv");
        Path targetPath = Paths.get("src/main/resources/csv/processed");

        try {
            Files.move(fileToMovePath, targetPath.resolve(fileToMovePath.getFileName()));
        } catch (IOException e) {
            System.out.println("!!! Problem moving data file.");
        }

        return RepeatStatus.FINISHED;
    }
}
