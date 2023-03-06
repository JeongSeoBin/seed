FROM postgres:13.3

#초기 DB만들기
ENV POSTGRES_DB=customer_support_db
ENV POSTGRES_USER=customer_support
ENV POSTGRES_PASSWORD=tjqjthvmxmdnpdj

RUN apt-get update \
      && apt-get install -y --no-install-recommends \
            postgresql-13-postgis-3 \
			postgresql-13-postgis-3-scripts \
      && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /docker-entrypoint-initdb.d

#EXTENSION 추가 작업
COPY ./initdb-extension.sh /docker-entrypoint-initdb.d/extension.sh


#INIT DUMP 복원시 사용 , 복원안할시 주석
#COPY ./init_dump.sql /docker-entrypoint-initdb.d/

