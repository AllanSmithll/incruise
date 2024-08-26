package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.InternshipOffer;
import br.edu.pweb2.incruise.model.NullOpportunity;
import br.edu.pweb2.incruise.model.Opportunity;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OpportunityRepository {
    public static Integer nextId = 1;

    public static final List<Opportunity> oportunityList = new ArrayList<>();

    {
        CompanyRepository companyRepository = new CompanyRepository();

        Company c1 = companyRepository.find(1);
        InternshipOffer i1 = new InternshipOffer(0, "Programador Front-end", 40, 4.800,
                490.0, "Não ter sido preso", c1);
                System.out.println(i1);
                c1.addOpportunity(i1);
                this.add(i1);
                InternshipOffer i2 = new InternshipOffer(0, "Programador Back-end", 40, 5.300,
                560.0, "Não ter sido casado", c1);
                System.out.println(i2);
        c1.addOpportunity(i2);
        this.add(i2);

    }

    public void add(Opportunity opportunity) {
        opportunity.setId(nextId++);
        oportunityList.add(opportunity);
    }

    public List<Opportunity> list() {
        return oportunityList;
    }

    public void remove(Integer id) throws Exception {
        Opportunity offer = this.find(id);
        oportunityList.remove(offer);
    }

    public Opportunity find(Integer id) {
        for (Opportunity op : oportunityList) {
            if (op.equals(id))
                return op;
        }
        return new NullOpportunity();
    }

    /*
     * private Integer generateID(){
     *
     * List<Student> students = this.list();
     *
     * Integer id = 1;
     *
     * //percore a lista procurando o maior ID, ou uma possível para inserção;
     * for(Student student : students){
     * //checa se o id gerado é maior que o id Na lista
     * if(id >student)
     * break; //caso seja, pare de interar
     * id++; // se não, aumente o id e continue
     * }
     * return id;
     * }
     */
}