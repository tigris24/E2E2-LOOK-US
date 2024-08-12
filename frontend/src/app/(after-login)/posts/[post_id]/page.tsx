import { getPost } from '@/app/_api/post'

type Props = {
    params: { post_id: number }
    searchParams: { [key: string]: string | string[] | undefined }
}

// Dynamic Metadata generation for Style Feed
export async function generateMetadata({ params }: Props) {
    const post = await getPost(params.post_id)
    return {
        title: `Post by User ${post.userId}`,
        description: `Post content: ${post.postContent}`,
    }
}

export default async function Page({ params }: Props) {
    const post = await getPost(params.post_id)

    return (
        <>
            <h1>{`User ID : ${post.userId}`}</h1>
            <p>hashTag : {post.hashtagContents}</p>
            <p>postContent : {post.postContent}</p>
        </>
    )
}
