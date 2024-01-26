FROM ubuntu:latest
LABEL authors="xai"

ENTRYPOINT ["top", "-b"]