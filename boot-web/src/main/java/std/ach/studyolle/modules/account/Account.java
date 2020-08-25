package std.ach.studyolle.modules.account;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Account {

	@Id @GeneratedValue
    private Long id;
	
	@Column(unique = true)
    private String email;
	
	@Column(unique = true)
    private String nickname;
    private String password;
    private boolean emailVerified;
    private String emailCheckToken;
    private LocalDateTime joinedAt;
    private String bio;
    private String url;
    private String occupation;
    private String location;
    
    @Lob @Basic(fetch = FetchType.EAGER )
    private String profileImage;
    private boolean studyCreatedByEmail;
    private boolean studyCreatedByWeb;
    private boolean studyEnrollmentResultByEmail;
    private boolean studyEnrollmentResultByWeb;
    private boolean studyUpdatedByEmail;
    private boolean studyUpdatedByWeb;

    public void generateEmailCheckToken() {
       this.emailCheckToken = UUID.randomUUID().toString();
    }

    public void completeSignUp(){
        this.emailVerified=true;
        this.joinedAt = LocalDateTime.now();
    }
    public boolean isValidToken(String token) {
        return this.emailCheckToken.equals(token);
    }
}
