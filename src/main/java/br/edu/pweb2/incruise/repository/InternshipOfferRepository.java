package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.Competence;
import br.edu.pweb2.incruise.model.InternshipOffer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class InternshipOfferRepository {
    public static Integer nextId = 1;

    public static final List<InternshipOffer> offersList = new ArrayList<>();

    {
        CompanyRepository companyRepository = new CompanyRepository();

        Company c1 = companyRepository.find(3);
        InternshipOffer i1 = new InternshipOffer(0, "Programador Front-end", 40, 4.800,
                490.0, "Não ter sido preso", c1);

        c1.addOpportunity(i1);
        this.add(i1);
        InternshipOffer i2 = new InternshipOffer(0, "Programador Back-end", 40, 5.300,
                560.0, "Não ter sido casado", c1);
        c1.addOpportunity(i2);
        this.add(i2);

    }

    public void add(InternshipOffer internshipOffer) {
        internshipOffer.setId(nextId++);
        offersList.add(internshipOffer);
    }

    public List<InternshipOffer> list() {
        return offersList;
    }

    public void remove(Integer id) throws Exception {
        InternshipOffer offer = this.find(id);
        offersList.remove(offer);
    }

    public InternshipOffer find(Integer id) throws Exception {
        for (InternshipOffer i : offersList) {
            if (i.getId().equals(id))
                return i;
        }
        throw new Exception("Offer not Found");
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
     * if(id >student.getId())
     * break; //caso seja, pare de interar
     * id++; // se não, aumente o id e continue
     * }
     * return id;
     * }
     */
}
