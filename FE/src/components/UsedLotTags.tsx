import { getUsedLotTags } from "@/api/account";
import { useLoginStore } from "@/stores/useLoginStore";
import { ReqTags } from "@/types/CommonType";
import { useState, useEffect } from "react";

export default function UsedLotTags() {
  const { user } = useLoginStore();
  const userSeq = user?.member.seq;
  const requestMostUsedTagDTO = {
    userSeq: userSeq!,
    count: 6,
  };

  const [tags, setTags] = useState<string[] | null>(null);

  const getTags = async () => {
    try {
      const data = await getUsedLotTags(requestMostUsedTagDTO);
      setTags(data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    getTags();
  }, [user]);

  return (
    <div className="flex flex-wrap w-full">
      {tags?.length ? (
        tags?.map((tag, i) => (
          <div key={i} className="w-1/2 py-2">
            <div className="w-3/4 bg-gray-300 rounded-lg fcc">{tag}</div>
          </div>
        ))
      ) : (
        <div className="w-1/2 py-2">사용한 태그가 없습니다.</div>
      )}
    </div>
  );
}