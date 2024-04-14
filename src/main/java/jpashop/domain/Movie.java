package jpashop.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("M")
public class Movie extends Item{

    private String director;
    private String actor;


}
