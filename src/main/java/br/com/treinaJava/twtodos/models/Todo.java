package br.com.treinaJava.twtodos.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título não pode ser vazio")
    @Size(min = 3, max = 100, message = "Título deve ter entre 3 e 100 caracteres")
    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @FutureOrPresent(message = "Data de entrega não pode ser no passado")
    @NotNull(message = "Data de entrega não pode ser nula")
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deadline;

    @Column(nullable = false)
    @Size(min = 3, max = 500, message = "Descrição deve ter entre 3 e 500 caracteres")
    private String description;
    
    @Column(nullable = true)
    private LocalDate finishedAt;

    public Todo() {
        this.createdAt = LocalDateTime.now();
    }
    public void finish() {
        this.finishedAt = LocalDate.now();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDate finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getDescription() {
        return description;
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
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", deadline=" + deadline +
                ", finishedAt=" + finishedAt +
                '}';
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
