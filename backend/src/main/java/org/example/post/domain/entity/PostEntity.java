package org.example.post.domain.entity;

import java.util.List;

import org.example.common.TimeTrackableEntity;
import org.example.post.domain.enums.PostStatus;
import org.example.user.domain.entity.member.UserEntity;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends TimeTrackableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long postId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@Column(nullable = false)
	private Long imageId;

	@Column(name = "post_content", nullable = true, columnDefinition = "VARCHAR(255)")
	private String postContent;

	@Column(name = "post_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private PostStatus postStatus; //TODO: build() 에서 제외

	@Column(name = "like_count", nullable = false, columnDefinition = "INT")
	@ColumnDefault("0")
	private Integer likeCount = 0;

	@Setter
	@OneToMany(mappedBy = "postEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HashtagEntity> hashtags;

/*	@OneToMany(mappedBy = "postEntity", fetch = FetchType.LAZY)
	private List<UserPostLikesEntity> likesList;*/

	public PostEntity(UserEntity user, String postContent, Long imageId, List<HashtagEntity> hashtags) {
		this.user = user;
		this.postContent = postContent;
		this.imageId = imageId;
		this.hashtags = hashtags;
		this.likeCount = 0;
		this.postStatus = PostStatus.PUBLISHED;
		// this.createdAt = createdAt;
		// this.updatedAt = updatedAt;
	}


	// convert List<HashtagEntity> to List<String>
	public List<String> getHashtagContents() {
		return hashtags.stream()
			.map(HashtagEntity::getHashtagContent)
			.toList();
	}

}
