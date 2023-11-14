"use client";
import BoardItem from "@/components/BoardItem";
import Searchbar from "@/components/Searchbar/Searchbar";
import { useLoginStore } from "@/stores/useLoginStore";
import { SearchParams } from "@/types/TroubleType";
import React, { useState } from "react";
import useInfiniteList from "@/hooks/useInfiniteList";

export default function BoardList() {
  const 
  const { user } = useLoginStore();
  const [options, setOptions] = useState<SearchParams>({
    ...(user && { loginSeq: user.member.seq }),
  });
  const { data } = useInfiniteList({ options: options, queryKey: "trouble" });
  // const { data, error } = useQuery({
  //   queryKey: ["boards"],
  //   queryFn: async () => {
  //     const data = await getTrouble(options);
  //     console.log(options);
  //     return data;
  //   },
  // });

  return (
    <>
      <Searchbar setPropsOptions={setOptions} baseUrl="/trouble" />
      <div className="bg-white rounded-lg shadow-md px-2 mt-2 flex-col items-center">
        {data &&
          data.pages.map((page, i) => (
            <React.Fragment key={i}>
              {page.troubleShootingList.map((content, idx) => (
                <BoardItem
                  nowUrl="trouble"
                  key={idx}
                  board={content}
                  idx={idx}
                  last={page.troubleShootingList.length - 1}
                  queryKey="boards"
                />
              ))}
            </React.Fragment>
          ))}
      </div>
    </>
  );
}
