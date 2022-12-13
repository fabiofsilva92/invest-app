package com.app.invest.service;

import com.app.invest.model.Pessoa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@Slf4j
public class FileService {

    private String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\script-db.txt";
    private File dbFile = new File(filePath);

    public void generateInsertPessoa(Pessoa pessoa) throws IOException {
        if(this.dbFile.createNewFile()){
            log.info("Arquivo criado");
        }
        try {
            FileWriter myWriter = new FileWriter(this.dbFile, true);
            BufferedWriter br = new BufferedWriter(myWriter);
            PrintWriter pr = new PrintWriter(br);

            pr.println("insert into pessoa (nome) values ('"+pessoa.getNome()+"');");

            log.info("Successfully wrote to the file.");
            pr.close();
            br.close();
            myWriter.close();
        } catch (IOException e) {
            log.info("An error occurred.");
            e.printStackTrace();
        }
    }

    public void generateUpdatePessoa(Pessoa pessoa) throws IOException {
        if(this.dbFile.createNewFile()){
            log.info("Arquivo criado");
        }
        try {
            FileWriter myWriter = new FileWriter(this.dbFile, true);
            BufferedWriter br = new BufferedWriter(myWriter);
            PrintWriter pr = new PrintWriter(br);

            pr.println("update pessoa set nome = '"+pessoa.getNome()+"' where id = "+pessoa.getId());

            log.info("Successfully wrote to the file.");
            pr.close();
            br.close();
            myWriter.close();
        } catch (IOException e) {
            log.info("An error occurred.");
            e.printStackTrace();
        }
    }

}
