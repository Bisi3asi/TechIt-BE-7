import Comment from './Comment';


const comments = [
    {
        name: "작성자 1",
        comment : "안녕하세요"
    },
    {
        name: "작성자 2",
        comment : "감사해요"
    },
    {
        name: "작성자 3",
        comment : "잘있어요"
    },
    {
        name: "작성자 4",
        comment : "다시만나요"
    }
];


function CommentList(props) {
    
return (
        <div>
            {comments.map((comment) => {
                return (
                    <Comment name = {comment.name} comment={comment.comment} />
                );
            })}
        </div>
    );
}

export default CommentList;