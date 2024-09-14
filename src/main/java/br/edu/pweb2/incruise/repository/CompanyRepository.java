//package br.edu.pweb2.incruise.repository;
//
//import br.edu.pweb2.incruise.model.Company;
//import br.edu.pweb2.incruise.model.ItemNotFoundException;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//public class CompanyRepository {
//
//    public static Long nextId = 1L;
//
//    public static final List<Company> companyList = new ArrayList<>();
//
//    {
//        this.add(new Company(0L, "Itaipava", "ita@gmail", "123", "Itaipava Cervejas", "1234", "(11) 98878-5324", "Bob", "São Paulo 123 capital", "Bebidas", "https://cervejaitaipava.com.br/"));
//        this.add(new Company(0L, "Ambev", "amb@gmail", "123", "Ambev Cervejas", "1234", "(11) 98878-3242", "Carlos", "Rio de Janeiro 123 capital", "Bebidas", "https://www.ambev.com.br/"));
//        this.add(new Company(0L, "Solar", "cocaCola@gmail", "123", "Solar", "123445", "(11) 98878-3242", "Seu Zé", "Paraíba Rua Caravelas", "Bebidas", "https://www.ambev.com.br/"));
//    }
//
//    public void add(Company company){
//        company.setId(nextId++);
//        companyList.add(company);
//    }
//
//    public List<Company> list(){
//        return companyList;
//    }
//
//    public void remove(Integer id) throws Exception {
//        Company company = this.find(id);
//        companyList.remove(company);
//    }
//
//    public Company find(Integer id) throws ItemNotFoundException {
//        return companyList.get(id);
//    }
//
//    public void update(Company company) {
//    }
//
///*
//    private Integer generateID(){
//        List<Student> students = this.list();
//        Integer id = 1;
//        //percore a lista procurando o maior ID, ou uma possível para inserção;
//        for(Student student : students){
//            //checa se o id gerado é maior que o id Na lista
//            if(id >student.getId())
//                break; //caso seja, pare de interar
//            id++; // se não, aumente o id e continue
//        }
//        return id;
//    }*/
//}