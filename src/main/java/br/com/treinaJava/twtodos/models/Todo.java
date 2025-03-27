package br.com.treinaJava.twtodos.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @Column(nullable=false)
    private LocalDate deadLine;

    @Column(nullable=true)
    private LocalDate finesheadAt;



    public Todo () {
        this.createdAt = LocalDateTime.now();
    }



    public void setId(Long id) {
        this.id = id;
    }



    public void setTitle(String title) {
        this.title = title;
    }



    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }



    public void setFinesheadAt(LocalDate finesheadAt) {
        this.finesheadAt = finesheadAt;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Todo other = (Todo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Todo{");
        sb.append("id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", deadLine=").append(deadLine);
        sb.append(", finesheadAt=").append(finesheadAt);
        sb.append('}');
        return sb.toString();
    }
    
    
}
