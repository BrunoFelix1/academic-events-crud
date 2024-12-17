
package models;



import java.io.Serializable;

import jakarta.persistence.Embeddable;



@Embeddable

public class InscricaoId implements Serializable {

    private Long usuarioId;

    private Long eventoId;



    // Getters, setters, hashCode, equals

    public Long getUsuarioId() {

        return usuarioId;

    }



    public void setUsuarioId(Long usuarioId) {

        this.usuarioId = usuarioId;

    }



    public Long getEventoId() {

        return eventoId;

    }



    public void setEventoId(Long eventoId) {

        this.eventoId = eventoId;

    }



    @Override

    public int hashCode() {

        final int prime = 31;

        int result = 1;

        result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());

        result = prime * result + ((eventoId == null) ? 0 : eventoId.hashCode());

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

        InscricaoId other = (InscricaoId) obj;

        if (usuarioId == null) {

            if (other.usuarioId != null)

                return false;

        } else if (!usuarioId.equals(other.usuarioId))

            return false;

        if (eventoId == null) {

            if (other.eventoId != null)

                return false;

        } else if (!eventoId.equals(other.eventoId))

            return false;

        return true;

    }

}
