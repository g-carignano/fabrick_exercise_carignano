package com.example.fabrick_exercise_carignano.localservice.batch;

import com.example.fabrick_exercise_carignano.repositories.csv.TransactionCsv;
import com.example.fabrick_exercise_carignano.repositories.dao.Transaction;
import com.example.fabrick_exercise_carignano.utils.DateUtils;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransactionBatchService {

    private final EntityManagerFactory emf;

    public TransactionBatchService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @StepScope
    public JpaPagingItemReader<Transaction> createReader(){

        Date start = DateUtils.startOfYesterday();
        Date end = DateUtils.endOfYesterday();

        JpaPagingItemReader<Transaction> reader = new JpaPagingItemReader<>();

        reader.setEntityManagerFactory(emf);
        reader.setQueryString("SELECT t FROM Transaction t WHERE t.paymentDate BETWEEN :start AND :end");

        Map<String,Object> params = new HashMap<>();
        params.put("start", start);
        params.put("end", end);
        reader.setParameterValues(params);

        reader.setPageSize(50);

        return reader;
    }

    @StepScope
    public FlatFileItemWriter<TransactionCsv> createWriter() {
        LocalDate yesterday = DateUtils.getYesterdayLocalDate();

        FlatFileItemWriter<TransactionCsv> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource(yesterday + "-transactions.csv"));
        writer.setHeaderCallback(w -> w.write("TransactionGuid, amount, currency, paymentDate, state, circuitType, shopName, buyerEmail, issuer"));
        writer.setLineAggregator(new DelimitedLineAggregator<>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<>() {{
                setNames(new String[]{"transactionGuid", "amount", "currency", "paymentDate", "state", "circuitType", "shopName", "buyerEmail", "issuer"});
            }});
        }});
        return writer;
    }

    public ItemProcessor<Transaction, TransactionCsv> transactionProcessor() {
        return TransactionCsv::new;
    }

}
