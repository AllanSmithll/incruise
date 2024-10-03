//package br.edu.pweb2.incruise.repository;
//
//import br.edu.pweb2.incruise.model.InternshipOffer;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class InternshipOfferRepository {
//    public static Long nextId = 1L;
//
//    public static final List<InternshipOffer> internshipOfferList = new ArrayList<>();
//
//    public void add(InternshipOffer internshipOffer) {
//        internshipOffer.setId(nextId++);
//        internshipOfferList.add(internshipOffer);
//    }
//
//    public List<InternshipOffer> list() {
//        return internshipOfferList;
//    }
//
//    public void remove(Integer id) throws Exception {
//        InternshipOffer offer = this.find(id);
//        internshipOfferList.remove(offer);
//    }
//
//    public InternshipOffer find(Integer id) {
//        return internshipOfferList.get(id);
//    }
//
//    /*
//     * private Integer generateID(){
//     *
//     * List<Student> students = this.list();
//     *
//     * Integer id = 1;
//     *
//     * //percore a lista procurando o maior ID, ou uma possível para inserção;
//     * for(Student student : students){
//     * //checa se o id gerado é maior que o id Na lista
//     * if(id >student)
//     * break; //caso seja, pare de interar
//     * id++; // se não, aumente o id e continue
//     * }
//     * return id;
//     * }
//     */
//}