import { useEffect, useState } from "react";
import useCounter from "./useCounter";


const MAX_CAPACITY = 10;

function Accommodate(props) {
    const [isFull, setIsFull] = useState(false);
    const [count, increaseCount, decreaseCount] = useCounter(0);

    useEffect(() => {
        console.log("======================");
        console.log("useEffect() is called.");
        console.log(`isFull: ${isFull}`);
    }); // 별도 의존성 배열이 없기 때문에 컴포넌트가 업데이트될 때(매번 마운트, 언마운트)마다 실행

    useEffect(() => {
        setIsFull(count >= MAX_CAPACITY);
        console.log(`Current count value: ${count}`)    
    }, [count]); // count가 변경될 때마다 실행

    return (
        <div className="p-16">
            <p>{`총 ${count}명 수용했습니다.`}</p>
            <button onClick={increaseCount} disabled={isFull} className="btn btn-success mr-1 text-center">입장</button>
            <button onClick={decreaseCount} className="btn btn-warning justify-center">퇴장</button>
            {isFull && <p className="text-red-600">정원이 가득찼습니다.</p>}
        </div>
    );
}

export default Accommodate;